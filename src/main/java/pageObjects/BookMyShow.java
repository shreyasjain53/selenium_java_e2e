package pageObjects;

import java.util.List;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookMyShow {

	public BookMyShow(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='sc-fHSTwm zreGp']")
	public WebElement viewAllCities;
	
	@FindBy(xpath="(//ul[@class='sc-hdPSEv gRNTvp'])//div")
	public List<WebElement> cityList;
	
	
	public void selectCity(String city , Logger log) {
		try {
	        for (WebElement citySelect : cityList) {
	            if (citySelect.getText().equalsIgnoreCase(city)) {
	                citySelect.click();
	                log.info(city +" "+ "selected from the suggestion list");
	                break;
	            }
	        }
	    } catch (Exception e) {
	    	log.info("An exception occurred while selecting the city: "+ city);
	    	log.error("Exception details: " + e.getMessage());
	    }
	}

	

}
