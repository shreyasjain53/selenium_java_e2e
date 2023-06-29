package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageShopping {

	public WebDriver driver;

	public HomePageShopping(WebDriver driver) {
		this.driver = driver;
	}

	By hamBurgerMenu = By.id("react-burger-menu-btn");
	By about = By.id("about_sidebar_link");
	By hamburgerCloseIcon = By.id("react-burger-cross-btn");
	By productText = By.xpath("//div[@class='header_secondary_container']/span");
	By productPrice = By.xpath("//div[@class='inventory_item_price']");
	By addToCart = By.xpath("//div[@class='pricebar']/button");
	By cart = By.xpath("//a[@class='shopping_cart_link']");
	By CartText = By.xpath("//div[@class='header_secondary_container']/span");
	By checkOut = By.id("checkout");
	By firstName = By.id("first-name");
	By lastName = By.id("last-name");
	By zipCode = By.id("postal-code");
	By continueBtn = By.id("continue");
	By CheckOutOverView = By.xpath("//span[@class='title']");

	public String getCheckOutOverView() {
		return driver.findElement(CheckOutOverView).getText();
	}

	public void getcontinueBtn() {
		driver.findElement(continueBtn).click();
	}

	public void enterDetails(String firstname, String lastname, String zip) {
		driver.findElement(firstName).sendKeys(firstname);
		driver.findElement(lastName).sendKeys(lastname);
		driver.findElement(zipCode).sendKeys(zip);
	}

	public void getcheckOut() {
		driver.findElement(checkOut).click();
	}

	public String getCartText() {
		return driver.findElement(CartText).getText();
	}

	public void getCart() {
		driver.findElement(cart).click();
	}

	public void getProductPrice() {
		List<WebElement> elements = driver.findElements(productPrice);
		ArrayList<Float> lst = new ArrayList<>();

		// $29.99
		int value = 0;

		for (WebElement ele : elements) {
			StringBuffer sb = new StringBuffer(ele.getText());
			StringBuffer price = sb.deleteCharAt(0);
			StringBuffer Newprice = price.delete(2, 5);

			lst.add(Float.valueOf((Newprice.toString())));

		}

		int highestIndex = 0;
		float highestValue = lst.get(0);

		for (int i = 1; i < lst.size(); i++) {
			float currentValue = lst.get(i);
			if (currentValue > highestValue) {
				highestValue = currentValue;
				highestIndex = i;
			}
		}
		int updatedPath = highestIndex + 1;

		driver.findElement(By.xpath("(//*[contains(text(),'Add to cart')])[" + updatedPath + "]")).click();

	}

	public String getproductText() {
		return driver.findElement(productText).getText();
	}

	public WebElement gethamburgerCloseIcon() {
		return driver.findElement(hamburgerCloseIcon);
	}

	public WebElement gethamBurgerMenu() {
		return driver.findElement(hamBurgerMenu);
	}

	public WebElement getabout() {
		return driver.findElement(about);
	}

}
