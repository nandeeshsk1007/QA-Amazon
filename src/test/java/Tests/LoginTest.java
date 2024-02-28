package Tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import BaseData.BaseClass;
import PageObjects.HomePage;
import PageObjects.SideMenuPage;
import PageObjects.SigninPage;
import PageObjects.WelcomePage;
public class LoginTest extends BaseClass{
	
	public void beforeTest() throws Exception
	{
		WelcomePage welcomePage=new WelcomePage(driver);
		welcomePage.clickOnHamberger();
		SideMenuPage sideMenuPage=new SideMenuPage(driver);
		sideMenuPage.clickOnSignin();
		Thread.sleep(2000);
	}
	@Test(priority = 1)
	public void loginWithValidCredentials() throws Exception
	{
		LoginTest before = new LoginTest();
		before.beforeTest();
		SigninPage signinPage = new SigninPage(driver);
		signinPage.enterEmailIntoTextfield("8050373728");
		signinPage.clickOnContinueButton();
		Thread.sleep(2000);
		signinPage.enterPasswordIntoTextfield("Yashwin@123");
		signinPage.clickOnSigninButton();
		Thread.sleep(2000);
		HomePage homePage = new HomePage(driver);
		
		Assert.assertTrue(homePage.signinButton(), "User is logged into the account"); 
		}
	
	@Test(priority = 2)
	public void loginWithInvalidEmailId() throws Exception
	{
		LoginTest before = new LoginTest();
		before.beforeTest();
		SigninPage signinPage = new SigninPage(driver);
		signinPage.enterEmailIntoTextfield("nandeeshsk1007@gmail.com");
		signinPage.clickOnContinueButton();
		Thread.sleep(2000);
		Assert.assertEquals(signinPage.emailWarningMessage(),"We cannot find an account with that email address", "Displayed wrong warning message");
	}
	
	@Test(priority = 3)
	public void loginWithInvalidMobileNumber() throws Exception
	{
		LoginTest before = new LoginTest();
		before.beforeTest();
		SigninPage signinPage = new SigninPage(driver);
		signinPage.enterEmailIntoTextfield("12345");
		signinPage.clickOnContinueButton();
		Thread.sleep(2000);
		Assert.assertEquals(signinPage.emailWarningMessage(),"We cannot find an account with that mobile number", "Displayed wrong warning message");
	}
	
	@Test(priority = 4)
	public void loginWithInvalidPassword() throws Exception
	{
		LoginTest before = new LoginTest();
		before.beforeTest();
		SigninPage signinPage = new SigninPage(driver);
		signinPage.enterEmailIntoTextfield("8050373728");
		signinPage.clickOnContinueButton();
		Thread.sleep(2000);
		signinPage.enterPasswordIntoTextfield("Abc@123");
		signinPage.clickOnSigninButton();
		Thread.sleep(2000);
		Assert.assertEquals(signinPage.passwordWarningMessage(),"Your password is incorrect", "Displayed wrong warning message");
	}
	
	@Test(priority = 5)
	public void loginWithoutCredentials() throws Exception
	{
		LoginTest before = new LoginTest();
		before.beforeTest();
		SigninPage signinPage = new SigninPage(driver);
		signinPage.clickOnContinueButton();
		Thread.sleep(2000);
		Assert.assertEquals(signinPage.warningMessage(),"Enter your email or mobile phone number", "Displayed wrong warning message");
	}
}