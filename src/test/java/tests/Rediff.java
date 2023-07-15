package tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;

public class Rediff extends BaseClass {
	WebDriver driver;

	@BeforeClass
	public void launch() throws IOException {
		driver = launchBrowser();
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}

	@Test
	public void rediffNews() {
		driver.get("https://www.rediff.com/");

		driver.findElement(By.xpath("//div[@id='tabtable']/div[2]")).click();
		driver.findElement(By.xpath("(//p[@class='alignR'])[1]/a")).click();
		driver.findElement(By.id("bytime")).click();
		driver.findElement(By.xpath("//div[@id='div_timeoptions']/ul/li[2]")).click();
		driver.findElement(By.id("sortby")).click();
		driver.findElement(By.xpath("//div[@id='div_sortoptions']/ul/li[1]")).click();

		List<WebElement> results = driver.findElements(By.xpath("//div[@id='div_srchresult']/div/div[2]/a/h4"));

		for (WebElement ele : results) {
			System.out.println(ele.getText());
		}
	}
}
