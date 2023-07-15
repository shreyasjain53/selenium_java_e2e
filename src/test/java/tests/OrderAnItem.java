package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import action.Action;
import base.BaseClass;
import pageObjects.HomePageShopping;
import pageObjects.LoginPageShopping;
import org.apache.logging.log4j.Logger;



public class OrderAnItem extends BaseClass {
	
	private static final Logger log = LogManager.getLogger(OrderAnItem.class);

	public WebDriver driver;

	// data

	String url = "https://www.saucedemo.com/";
	String ridectionURL = "https://saucelabs.com/site";
	String productText = "Products";
	String firstname = "Shreyas";
	String lastname = "Kumar";
	String zip = "574210";
	String yourcart = "Your Cart";
	String checkout = "Checkout: Overview";

	@BeforeClass
	public void browserLaunch() throws IOException {
		driver = launchBrowser();
		driver.navigate().to(url);
	}

	@AfterClass(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void orderAnItemFromSauce() throws InterruptedException {
		LoginPageShopping lp = new LoginPageShopping(driver);
		HomePageShopping hp = new HomePageShopping(driver);
		SoftAssert softAssert = new SoftAssert();
		Action act = new Action(driver);

		lp.loginToSauceDemo("standard_user", "secret_sauce");
		hp.gethamBurgerMenu().click();
		hp.getabout().click();

		//softAssert.assertEquals(driver.getCurrentUrl(), ridectionURL);

		// Perform back button
		driver.navigate().back();
		Thread.sleep(10000);

		act.explicitWait( hp.gethamburgerCloseIcon(), 20);
		hp.gethamburgerCloseIcon().click();
		act.explicitWait( hp.gethamburgerCloseIcon(), 10);

		softAssert.assertEquals(hp.getproductText(), productText,
				"Page redirecting to other URL" + "=" + driver.getCurrentUrl());

		hp.getProductPrice();

		hp.getCart();

		softAssert.assertEquals(hp.getCartText(), yourcart);
		hp.getcheckOut();
		

		hp.enterDetails(firstname, lastname, zip);
		hp.getcontinueBtn();

		softAssert.assertEquals(hp.getCheckOutOverView(), checkout);

		log.info("Shopping cart test passed");
		softAssert.assertAll();
		
	}

}
