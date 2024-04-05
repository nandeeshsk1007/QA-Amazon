package Others;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Ab2 {

	private static LinkedList<String> sub;
	private static WebElement mul1;

	public static void main(String[] args) throws InterruptedException {
		// Set the path to ChromeDriver
		WebDriverManager.firefoxdriver().setup();

		// Initialize ChromeDriver
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Start URL
		//LinkedList<String> startUrl = new LinkedList<String>();
		//startUrl.add("https://en.wikipedia.org/wiki/Category:All_articles_with_dead_external_links?from=A");
		//		startUrl.add("https://en.wikipedia.org/wiki/Baba_Sali#External_links");
		//		startUrl.add("https://en.wikipedia.org/wiki/Da%27_Dip#References");
		//		startUrl.add("https://en.wikipedia.org/wiki/Alba_D%27Urbano#External_links");
		
		LinkedList<String> startUrl = new LinkedList<String>();
		startUrl.add("https://en.wikipedia.org/wiki/Category:All_articles_with_dead_external_links?from=L");
		
		for(String all:startUrl) {
			scrapeDeadLinks(driver, wait, all);
			while(true) {
				if(driver.findElement(By.xpath("//a[.='next page']")).isDisplayed()) {
					driver.findElement(By.xpath("//a[.='next page']")).click();
					scrapeDeadLink(driver, wait);
					continue;
				}
				else {
					System.out.println("No element");
					break;
				}
			}
			System.out.println("----------------------------------------------------------------------------------");
		}
		
		
	}
	
	public static void scrapeDeadLink(WebDriver driver, WebDriverWait wait) {
		List<WebElement> mul = driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div/div/ul/li"));
		for(int i=1; i<=mul.size(); i++) {
			
			mul1 = driver.findElement(By.xpath("//*[@id=\"mw-pages\"]/div/div/div/ul/li["+i+"]/a"));
			//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", mul1);
			System.out.println(mul1.getAttribute("href"));
		}
	}

	public static void scrapeDeadLinks(WebDriver driver, WebDriverWait wait, String url) throws InterruptedException {
		// Navigate to the start URL
		driver.get(url);

		List<WebElement> mul = driver.findElements(By.xpath("//*[@id=\"mw-pages\"]/div/div/div/ul/li"));
		for(int i=1; i<=mul.size(); i++) {
			mul1 = driver.findElement(By.xpath("//*[@id=\"mw-pages\"]/div/div/div/ul/li["+i+"]/a"));
			//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", mul1);
			System.out.println(mul1.getAttribute("href"));
			
		}
		

		//Thread.sleep(2000);
		//} catch (StaleElementReferenceException e) {
		//				driver.navigate().refresh();
		//				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", mul1);
		//				mul1.click();
		//				Thread.sleep(2000);
		//			}
		//			List<WebElement> text = driver.findElements(By.xpath("//li"));
		//
		//			for(WebElement all:text) {
		//
		//				if(all.getAttribute("outerHTML").contains("dead link")) {
		//					String href = all.getAttribute("outerHTML");
		//					System.out.println(href);
		//					String hrefValue = extractHrefValue(href);
		//					System.out.println(url+"--------"+hrefValue);
		//				}
		//			}
		//driver.navigate().back();
	}


	public static String extractHrefValue(String htmlCode) {
		int startIndex = htmlCode.indexOf("href=\"http");
		if (startIndex != -1) {
			startIndex += "href=\"".length(); 
			int endIndex = htmlCode.indexOf("\"", startIndex); 
			if (endIndex != -1) {
				return htmlCode.substring(startIndex, endIndex); 
			}
		}

		return "";
	}
}
