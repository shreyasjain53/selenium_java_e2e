package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;

public class DataProviderLoginTest extends BaseClass {

	private static final Logger log = LogManager.getLogger(DataProviderLoginTest.class);

	public WebDriver driver;

	@BeforeClass
	public void browserLaunch() throws IOException {
		driver = launchBrowser();
	}

	@AfterClass(enabled = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@DataProvider(name = "Authentication")
	public static Object[][] credentials() {

		return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_2", "Test@123" },
				{ "testuser_3", "Test@123" } };
	}

	// ,threadPoolSize=5
	@Test(dataProvider = "Authentication", invocationCount = 1)
	public void test(String userName, String password) throws InterruptedException {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("(//button[normalize-space()='Log in'])[1]")).click();
	}
}
