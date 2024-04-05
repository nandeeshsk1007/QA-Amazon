package Others;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Chat1 {

    public static void main(String[] args) throws IOException {
        // Set the path to ChromeDriver
        WebDriverManager.firefoxdriver().setup();

        // Initialize ChromeDriver
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Load the Excel file
        String excelFilePath = "urls.xlsx";
        Workbook workbook = WorkbookFactory.create(new FileInputStream(excelFilePath));
        Sheet sheet = workbook.getSheetAt(0);

        // Create a workbook for storing results
        Workbook resultWorkbook = new XSSFWorkbook();
        Sheet resultSheet = resultWorkbook.createSheet("Links");

        // Function to scrape dead links
        int rowNum = 0;
        for (Row row : sheet) {
            String url = row.getCell(0).getStringCellValue(); // Assuming URL is in the first column
            if (url != null && !url.isEmpty()) {
                rowNum = scrapeDeadLinks(driver, wait, url, resultSheet, rowNum);
            }
        }

        // Write the workbook content to a file
        FileOutputStream fileOut = new FileOutputStream("links.xlsx");
        resultWorkbook.write(fileOut);
        fileOut.close();

        // Close the workbooks and the WebDriver
        workbook.close();
        resultWorkbook.close();
        driver.quit();
    }

    public static int scrapeDeadLinks(WebDriver driver, WebDriverWait wait, String url, Sheet sheet, int rowNum)
            throws EncryptedDocumentException, IOException {
        // Navigate to the URL
        driver.get(url);

        List<WebElement> text = driver.findElements(By.xpath("//li"));

        int currentRow = rowNum;
        for (WebElement element : text) {
            if (element.getAttribute("outerHTML").contains("dead link")) {
                String href = element.getAttribute("outerHTML");
                String hrefValue = extractHrefValue(href);
                String name_of_page = driver.findElement(By.xpath("//main/header/h1")).getText();
                System.out.println(url + "---" + name_of_page + "----" + hrefValue);

                // Create a new row in the result sheet
                Row row = sheet.createRow(currentRow);
                // Create cells for URL, page name, and href value
                row.createCell(0).setCellValue(url);
                row.createCell(1).setCellValue(name_of_page);
                row.createCell(2).setCellValue(hrefValue);
                currentRow++;
            }
        }
        driver.navigate().back();
        return currentRow;
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
