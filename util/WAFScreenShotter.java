package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class WAFScreenShotter {

    public WAFScreenShotter(WebDriver driver, String filename) {
        takeScreenshot(driver, filename);
    }

    public void takeScreenshot(WebDriver driver, String filename) {
        File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(image, new File("./" + filename + ".jpg"));
            System.out.flush();
        } catch (IOException ioException) {
            System.out.println("IOException Occurred");
            System.out.println(ioException.toString());
            // Improve error catching/exiting
        }
    }
}
