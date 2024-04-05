package Others;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Ot {


	public static void main(String[] args) throws IOException {
		// Set the path to ChromeDriver
		WebDriverManager.firefoxdriver().setup();

		// Initialize ChromeDriver
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		String log4jConfPath = "/path/to/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

		// Start URL
		LinkedList<String> startUrl = new LinkedList<String>();
		startUrl.add("https://en.wikipedia.org/wiki/A_Barnyard_Frolic");
		startUrl.add("https://en.wikipedia.org/wiki/A_Bathing_Ape");
		startUrl.add("https://en.wikipedia.org/wiki/A_Boy_and_His_Blob:_Trouble_on_Blobolonia");
		startUrl.add("https://en.wikipedia.org/wiki/A_Broad_Bellflower");
		startUrl.add("https://en.wikipedia.org/wiki/A_Bucket_of_Blood");
		startUrl.add("https://en.wikipedia.org/wiki/A_Case_of_Spring_Fever");
		startUrl.add("https://en.wikipedia.org/wiki/%C3%80_cause_de_l%27automne");
		startUrl.add("https://en.wikipedia.org/wiki/A_Change_Is_Gonna_Come_(Grey%27s_Anatomy)");
		startUrl.add("https://en.wikipedia.org/wiki/A_Change_of_Pace");
		startUrl.add("https://en.wikipedia.org/wiki/A_Change_of_Skin");

		// Function to scrape dead links
		for(String all:startUrl) {
			scrapeDeadLinks(driver, wait, all);
		}
		// Close the WebDriver
		driver.quit();
	}


	public static void scrapeDeadLinks(WebDriver driver, WebDriverWait wait, String url) throws EncryptedDocumentException, IOException {
		// Navigate to the start URL
		driver.get(url);


		List<WebElement> text = driver.findElements(By.xpath("//li"));

		for(WebElement all:text) {

			if(all.getAttribute("outerHTML").contains("dead link")) {
				String href = all.getAttribute("outerHTML");
				//System.out.println(href);
				String hrefValue = extractHrefValue(href);
				String name_of_page = driver.findElement(By.xpath("//main/header/h1")).getText();
				System.out.println(url+"---"+name_of_page+"----"+hrefValue);

			}
		}
		driver.navigate().back();
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
