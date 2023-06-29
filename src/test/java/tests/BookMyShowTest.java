package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.BaseClass;
import pageObjects.BookMyShow;

public class BookMyShowTest extends BaseClass {

	private static final Logger log = LogManager.getLogger(BookMyShowTest.class);
	public WebDriver driver;
	
	@BeforeClass
	public void browserLaunch() throws IOException {
		driver = launchBrowser();

		// Maximize Window
		driver.manage().window().maximize();
		log.info("Browser Window Maximized");

		// Delete Cookies
		driver.manage().deleteAllCookies();
		log.info("cookies deleted");

	}
	
	@AfterClass(enabled = false)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
			log.info("Browser closed");
		}
	}

	@Test
	public void cityVerify() {
		
		BookMyShow bms = new BookMyShow(driver);

		driver.navigate().to(prop.getProperty("BookMyShowURL"));
		log.info(prop.get("BookMyShowURL")+" "+ "opened in browser");
		
		bms.viewAllCities.click();
		log.info("Clicked on view all cities");
		
		bms.selectCity(prop.getProperty("CityNeedsToBeSelect"), log);
		
		
	}

	
}
