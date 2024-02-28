package PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideMenuPage {
	
	WebDriver driver;
	@FindBy (xpath = "//a[text()='Sign in']")
	private WebElement signinbutton;
	
	
	public SideMenuPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnSignin()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Point loc = signinbutton.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		js.executeScript("window.scrollBy("+x+","+y+")");
		signinbutton.click();
	}
}
