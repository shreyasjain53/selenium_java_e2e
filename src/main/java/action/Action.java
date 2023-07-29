package action;

import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Action {
	
	private static final Logger log = LogManager.getLogger(Action.class);

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
		log.info("All coockies deleted");
	}

	// Window handle
	public String windowHandle() {
		return driver.getWindowHandle();
	}

	// window handles
	public Set<String> windowHandles() {
		return driver.getWindowHandles();
	}

	// Switch frame by ID
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
	
	

}
