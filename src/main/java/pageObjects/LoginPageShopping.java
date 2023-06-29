package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageShopping {

	public WebDriver driver;

	public LoginPageShopping(WebDriver driver) {
		this.driver = driver;
	}

	By userName = By.id("user-name");
	By password = By.id("password");
	By loginBtn = By.id("login-button");

	public void loginToSauceDemo(String username, String loginPassword) {
		driver.findElement(userName).sendKeys(username);
		driver.findElement(password).sendKeys(loginPassword);
		driver.findElement(loginBtn).click();
	}

}
