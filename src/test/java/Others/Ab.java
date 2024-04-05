package Others;

import com.opencsv.CSVWriter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Ab {
    public static void main(String[] args) {
        // Setup WebDriver using WebDriverManager
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        // Set implicit wait time
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Start URL
        String startUrl = "https://en.wikipedia.org/wiki/Category:All_articles_with_dead_external_links?from=A";

        // Function to scrape dead links and save to CSV
        scrapeDeadLinksAndHandlePagination(driver, startUrl);

        // Close the WebDriver
        driver.quit();
    }

    public static void scrapeDeadLinksAndHandlePagination(WebDriver driver, String url) {
        // Navigate to the start URL
        driver.get(url);

        try {
            // Create a CSV writer
            CSVWriter writer = new CSVWriter(new FileWriter("dead_links.csv"));

            // Scraping dead links and handling pagination
            while (true) {
                scrapeCurrentPageAndWriteToCSV(driver, writer);

                // Check if there is a next page
                List<WebElement> nextPageLinks = driver.findElements(By.cssSelector("#mw-pages a[title='Category:All articles with dead external links']"));

                if (nextPageLinks.size() == 0) {
                    break;
                }

                // Click on the next page link
                nextPageLinks.get(0).click();
            }

            // Close the CSV writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scrapeCurrentPageAndWriteToCSV(WebDriver driver, CSVWriter writer) {
        // Get all website name links
        List<WebElement> websiteNameLinks = driver.findElements(By.cssSelector(".mw-category-group a"));

        // Iterate through each website name link
        for (WebElement websiteNameLink : websiteNameLinks) {
            // Click on the website name link
            websiteNameLink.click();

            // Scrape dead links
            scrapeDeadLinks(driver, writer);

            // Go back to the previous page
            driver.navigate().back();
        }
    }

    public static void scrapeDeadLinks(WebDriver driver, CSVWriter writer) {
        List<WebElement> text = driver.findElements(By.xpath("//li"));

        for (WebElement all : text) {
            if (all.getAttribute("outerHTML").contains("dead link")) {
                String href = all.getAttribute("outerHTML");
                System.out.println(href);
                String hrefValue = extractHrefValue(href);
                System.out.println(driver.getCurrentUrl() + "--------" + hrefValue);
                writer.writeNext(new String[]{driver.getCurrentUrl(), hrefValue});
            }
        }
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
