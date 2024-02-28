package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;

	@FindBy(xpath="//span[.='Hello, Nandeesh']")
	private WebElement signinbutton;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean signinButton()
	{
		boolean displaystatus = signinbutton.isDisplayed();
		return displaystatus;
	}
}
