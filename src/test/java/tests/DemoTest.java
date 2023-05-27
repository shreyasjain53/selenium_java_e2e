package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.BaseClass;
import pageObjects.HomePage;
import java.io.IOException;
import java.time.Duration;

public class DemoTest extends BaseClass{

	private static final Logger log = LogManager.getLogger(DemoTest.class);
	public WebDriver driver;
	
	HomePage hp;
	SoftAssert assertion = new SoftAssert();
			
	@BeforeClass
	public void browserLaunch() throws IOException {
		driver =launchBrowser();

		// Maximize Window
		driver.manage().window().maximize();
		log.info(" Browser Window Maximized");

		// Delete Cookies
		driver.manage().deleteAllCookies();
		log.info("cookies deleted");

	}

	@Test(priority = 1)
	public void CustomerLogin() {
		hp = new HomePage(driver);
		
		// Invoke URL
		driver.get(prop.getProperty("URL"));
		log.info("URL got opened in browser");
		
		// Login as Customer
		Assert.assertEquals(hp.customerLogin.getText(), "Customer Login");
		log.info("Login as Customer");

		hp.customerLogin.click();
		
		System.out.println(driver.getTitle());
		log.info(driver.getTitle());

		// Select class to static Dropdown
		Select custLogin = new Select(hp.nameSelectBox);
		custLogin.selectByVisibleText(prop.getProperty("CustomerName"));
		log.info("Harry Potter selected from dropdown");

		hp.customerLoginBtn.click();

		Assert.assertEquals(hp.customerName.getText(), prop.getProperty("CustomerName"));

		log.info("Harry Potter verified");
	}

	@Test(priority = 2)
	public void BankManagerLogin() {
		hp = new HomePage(driver);
		
		// Navigate to HomePage
		hp.home.click();
		log.info("Navigated to HomePage");

		// Login as manager
		hp.managerLogin.click();
		log.info("Logged in as a manager");

		// Opening new account
		hp.openAccount.click();
		Assert.assertEquals(hp.process.getText(), prop.getProperty("processname"));
		log.info("Opening new account");

		// Select class to static Dropdown
		Select newCustMan = new Select(hp.newCustomerManager);
		newCustMan.selectByVisibleText(prop.getProperty("CustManager"));
		log.info("Select class to static Dropdown");

		// Select class to static Dropdown
		Select currencyFormat = new Select(hp.currency);
		currencyFormat.selectByVisibleText(prop.getProperty("CurrencyFormat"));
		log.info("Currency type selected");

		hp.process.click();

		// Switch to alert
		Alert myAlert = driver.switchTo().alert();
		String alertMsg = myAlert.getText();

		String[] success = alertMsg.split(" with");

		Assert.assertEquals(success[0], "Account created successfully");

		myAlert.accept();
		log.info("Account created successfully");

		hp.home.click();

		WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(10));
		myWait.until(ExpectedConditions.visibilityOf(hp.customerLogin));

		Actions act = new Actions(driver);
		act.build().perform();
	}

	@AfterTest
	public void tearDown() {
		// To close the browser
		driver.quit();
		log.info("Browser closed");
		log.debug("debug");
		log.fatal("fatal");
		log.error("error");
		log.warn("warn");
		log.info("info");
	}

	

}