package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SigninPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@type='email']")
	private WebElement emailtextfield;
	
	@FindBy(xpath="//input[@id='continue']")
	private WebElement continuebutton;
	
	@FindBy (xpath = "//span[contains(.,'We cannot find an account')]")
	private WebElement emailwarningmessage;
	
	@FindBy (xpath = "//input[@type='password']")
	private WebElement passwordtextfield;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	private WebElement signinbutton;
	
	@FindBy (xpath = "//span[contains(.,'Your password')]")
	private WebElement passwordwarningmessage;
	
	@FindBy (xpath = "//span[contains(.,'Enter your email or mobile phone number')]")
	private WebElement warningmessage;
	
	public SigninPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailIntoTextfield(String email)
	{
		emailtextfield.sendKeys(email);
	}
	public void clickOnContinueButton()
	{
		continuebutton.click();
	}
	public String emailWarningMessage()
	{
		String emailmessage=emailwarningmessage.getText();
		return emailmessage;
	}
	public void enterPasswordIntoTextfield(String password)
	{
		passwordtextfield.sendKeys(password);
	}
	public void clickOnSigninButton()
	{
		signinbutton.click();
	}
	public String passwordWarningMessage()
	{
		String passwordmessage = passwordwarningmessage.getText();
		return passwordmessage;
	}
	public String warningMessage()
	{
		String message = warningmessage.getText();
		return message;
	}
}
