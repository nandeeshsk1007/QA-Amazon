package qa.Amazon;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Camera {
	public static WebDriver driver;
	public static void main(String[] args) 
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://google.com/");
	}
}
