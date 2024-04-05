package Others;

import org.checkerframework.checker.units.qual.Length;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class GetLinks {
	public static void main(String[] args) {
		// Set the path to ChromeDriver
		WebDriverManager.firefoxdriver().setup();

		// Initialize ChromeDriver
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Start URL
		String startUrl = "https://en.wikipedia.org/wiki/Baba_Sali#External_links";

		// Function to scrape dead links
		scrapeDeadLinks(driver, wait, startUrl);

		// Close the WebDriver
		driver.quit();
	}

	public static void scrapeDeadLinks(WebDriver driver, WebDriverWait wait, String url) {
		// Navigate to the start URL
		driver.get(url);

		List<WebElement> text = driver.findElements(By.xpath("//li"));
		//		for(int i=1; i<=text.size(); i++) {
		int n=1;
		for(int j=1; j<=n; j++) {
			System.out.println(text.get(j).getText());
			List<WebElement> f = driver.findElements(By.xpath("//li["+j+"]/a"));
			System.out.println(f.size());
			for(int k=0; k<f.size(); k++) {
				//if(driver.findElements(By.xpath("//li["+j+"]/a")).get(k).getText().contains("dead link")) {
				System.out.println(driver.findElements(By.xpath("//li["+j+"]/a")).get(k).getText());
				System.out.println(driver.findElements(By.xpath("//li["+j+"]/a")).get(k).getAttribute("href")+"--------------------"+k);

				//	}
			}
			n++;
		}












		//		List<WebElement> text = driver.findElements(By.xpath("//li"));
		//		System.out.println(text.get(0).get);
		//		System.out.println(text.get(0).getAttribute("href"));





		//						    	   if(text.get(i).getText().contains("dead link"))
		//						    	   {
		//						 System.out.println(text.get(i).getText());
		//					
		//						 System.out.println(driver.findElements(By.xpath("//li/a")).get(i).getAttribute("href")+"--------------------");
		//						    	   }
		//					}



		//        // Wait for the content to load
		//        WebElement content = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".large-cat-toc-jumpy-title a:nth-of-type(3)")));
		//
		//        // Click on the content link
		//        content.click();
		//
		//        // Scraping dead links and handling pagination
		//        while (true) {
		//            scrapeCurrentPage(driver, wait);
		//
		//            // Check if there is a next page
		//            WebElement nextPageLink = driver.findElement(By.cssSelector("#mw-pages a[title='Category:All articles with dead external links']"));
		//
		//            if (!nextPageLink.getAttribute("title").equals("Category:All articles with dead external links")) {
		//                break;
		//            }
		//
		//            // Click on the next page link
		//            nextPageLink.click();
		//        }
	}

	//    public static void scrapeCurrentPage(WebDriver driver, WebDriverWait wait) {
	//        // Wait for the website names to load
	//        List<WebElement> websiteNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mw-category-group a")));
	//
	//        for (WebElement websiteName : websiteNames) {
	//            // Click on the website name link
	//            websiteName.click();
	//
	//            // Wait for the external links section to load
	//            WebElement externalLinks = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toc-External_links a")));
	//
	//            // Click on the external links section
	//            externalLinks.click();
	//
	//            // Get the dead link text
	//            List<WebElement> deadLinksText = driver.findElements(By.cssSelector("i span"));
	//            for (WebElement deadLinkText : deadLinksText) {
	//                System.out.println(deadLinkText.getText());
	//            }
	//
	//            // Get the list of links within external links
	//            List<WebElement> listOfLinks = driver.findElements(By.cssSelector(".mw-content-ltr li > a"));
	//            for (WebElement link : listOfLinks) {
	//                System.out.println(link.getAttribute("href"));
	//            }
	//
	//            // Go back to the previous page
	//            driver.navigate().back();
	//        }
	//    }
}

//}
