package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;

public class FaceBookLogin extends BaseClass {
	
	WebDriver driver;
	private static final Logger log = LogManager.getLogger(FaceBookLogin.class);
	
	@BeforeTest
	public void browserLaunch() throws IOException {
		driver = launchBrowser();
	}
	
	@Test
	public void facebookLogin() throws InterruptedException {
		
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys("facebook");
		driver.findElement(By.id("pass")).sendKeys("sdfsdfs");
		driver.findElement(By.name("login")).click();
		String errorMsg = driver.findElement(By.className("_9ay7")).getText();
		log.info(errorMsg);
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
