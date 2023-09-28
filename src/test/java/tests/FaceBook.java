package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;

public class FaceBook extends BaseClass {

	public WebDriver driver;
	private static final Logger log = LogManager.getLogger(FaceBook.class);

	@BeforeClass
	public void browserLaunch() throws IOException {
		driver = launchBrowser();
	}

	@Parameters({ "URL", "userName", "password" })
	@Test
	public void facebookLogin(@Optional("Optional Parameter") String url, String password, String username)
			throws InterruptedException {

		driver.get(url);
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.name("login")).click();
		String errorMsg = driver.findElement(By.className("_9ay7")).getText();
		log.info(errorMsg);
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
