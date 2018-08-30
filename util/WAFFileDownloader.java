package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class WAFFileDownloader {

    public WAFFileDownloader(WebDriver driver, String testFolderName, String cssSelector) {
        downloadFiles(driver, testFolderName, cssSelector);
    }

    public void downloadFiles(WebDriver driver, String testFolderName, String cssSelector) {

        File testFolder = new File(testFolderName);
        if (!testFolder.exists()) {
            testFolder.mkdir();
        }

        List<WebElement> elementList = driver.findElements(By.cssSelector(cssSelector));
        for (WebElement webElement : elementList) {
            URL link;
            String fileName;

            try {
                link = new URL(webElement.getAttribute("href"));
                fileName = link.toString().substring(link.toString().lastIndexOf('/') + 1).trim();

                if (!fileName.isEmpty()) {
                    File dest = new File(testFolderName + "/" + fileName);
                    try {
                        FileUtils.copyURLToFile(link, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
}
