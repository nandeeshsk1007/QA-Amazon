package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {

	WebDriver driver;
	
	@FindBy(xpath = "//i[@class='hm-icon nav-sprite']")
	private WebElement hamburger;
	
	public WelcomePage(WebDriver driver)
	{
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnHamberger()
	{
		hamburger.click();
	}
}
