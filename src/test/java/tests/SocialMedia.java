package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;

public class SocialMedia extends BaseClass {

	public WebDriver driver;
	private static final Logger log = LogManager.getLogger(SocialMedia.class);

	@BeforeTest
	public void browserLaunch() throws IOException {
		driver = launchBrowser();
	}

	@Test(priority = 1)
	public void facebookLogin() throws InterruptedException {

		driver.get("https://www.facebook.com/");
	}

	@Test(priority = 2)
	public void instagramLogin() throws InterruptedException {

		driver.get("https://www.instagram.com/");
	}

	@Test(priority = 3)
	public void youtubeLogin() throws InterruptedException {

		driver.get("https://www.youtube.com/");
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
