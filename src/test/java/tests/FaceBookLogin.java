package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;

public class FaceBookLogin extends BaseClass {
	
	WebDriver driver;
	
	@BeforeTest
	public void browserLaunch() throws IOException {
		driver = launchBrowser();
	}
	
	@Test
	public void facebookLogin() throws InterruptedException {
		
		driver.get("https://www.facebook.com/");
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("facebook");
		Thread.sleep(3000);
		driver.findElement(By.id("pass")).sendKeys("sdfsdfs");
		Thread.sleep(3000);
		driver.findElement(By.name("login")).click();
		String errorMsg = driver.findElement(By.className("_9ay7")).getText();
		System.out.println(errorMsg);
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

}
