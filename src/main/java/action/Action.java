package action;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Action {

	private WebDriver driver = null;

	public Action(WebDriver driver) {
		this.driver = driver;
	}

	public void explicitWait(WebElement ele, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.visibilityOf(ele));
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	// To type text in input field
	public void type(WebElement element, String text) {
		try {
			element.sendKeys(text);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	// To get current URL
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	// Delete all coockies
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	// Window handle
	public String windowHandle() {
		return driver.getWindowHandle();
	}

	// window handles
	public Set<String> windowHandles() {
		return driver.getWindowHandles();
	}

	// Swutch frame by ID
	public void switchFrameByID(String id) {
		try {
			driver.switchTo().frame(id);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	// scroll till visibility of an element
	public void scrollTillVisiblityOfElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	// Right click in selenium
	public void rightClick(WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).build().perform();
	}

	// double click on element
	public void doubleClick(WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).build().perform();
	}

	// drag and drop
	public void dragAndDrop(WebElement source, WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, target);
	}

	// takes screenshot of the page
	public void takeScreenShot() throws IOException {
		TakesScreenshot ts = ((TakesScreenshot) driver);
		File file = ts.getScreenshotAs(OutputType.FILE);
		String savePath = "E:\\learn\\Maven";
		FileUtils.copyFile(file, new File(savePath));
	}
	
	

}
