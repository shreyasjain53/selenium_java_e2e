package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver	, this);
		}

	@FindBy(xpath = "//button[@ng-click='customer()']")
	public WebElement customerLogin;
	
	@FindBy(xpath = "//select[@id='userSelect']")
	public WebElement nameSelectBox;
	
	@FindBy(xpath = "//button[@class='btn btn-default']")
	public WebElement customerLoginBtn;
	
	@FindBy(xpath = "//span[@class='fontBig ng-binding']")
	public WebElement customerName;
	
	@FindBy(xpath = "//button[@class='btn home']")
	public WebElement home;
	
	@FindBy(xpath = "//button[@ng-click='manager()']")
	public WebElement managerLogin;
	
	@FindBy(xpath = "//button[@ng-click='openAccount()']")
	public WebElement openAccount;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement process;
	
	@FindBy(xpath = "//select[@id='userSelect']")
	public WebElement newCustomerManager;
	
	@FindBy(xpath = "//select[@id='currency']")
	public WebElement currency;
	
	@FindBy(css = "button[ng-click='manager()']")
	public WebElement buttonBankManagerLogin;
	
	
	
	
	
	
}
