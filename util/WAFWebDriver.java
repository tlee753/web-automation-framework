package util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;


public class WAFWebDriver {

    private WebDriver driver;

    public WAFWebDriver(String browser, String width, String height, String port) throws Exception {

        DriverService service;
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        browser = browser.toLowerCase();
        int portValue = (port.isEmpty()) ? 0 : Integer.parseInt(port);

        if (portValue == 0) {
            if (operatingSystem.contains("windows")) {
                switch (browser) {
                    case "chrome":
                        service = new ChromeDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/chromedriver.exe"))
                                .build();
                        service.start();
                        driver = new ChromeDriver((ChromeDriverService) service);
                        break;
                    case "firefox":
                        service = new GeckoDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/geckodriver.exe"))
                                .build();
                        service.start();
                        driver = new FirefoxDriver((GeckoDriverService) service);
                        break;
                    case "ie":
                        service = new InternetExplorerDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/IEDriverServer.exe"))
                                .build();
                        service.start();
                        driver = new InternetExplorerDriver((InternetExplorerDriverService) service);
                        break;
                    case "edge":
                        service = new EdgeDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/MicrosoftWebDriver.exe"))
                                .build();
                        service.start();
                        driver = new EdgeDriver((EdgeDriverService) service);
                        break;
                    default:
                        throw new Exception("Browser not supported");
                }
            } else if (operatingSystem.contains("linux")) {
                switch (browser) {
                    case "chrome":
                        service = new ChromeDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/chromedriver"))
                                .build();
                        service.start();
                        driver = new ChromeDriver((ChromeDriverService) service);
                        break;
                    case "firefox":
                        service = new GeckoDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/geckodriver"))
                                .build();
                        service.start();
                        driver = new FirefoxDriver((GeckoDriverService) service);
                        break;
                    default:
                        throw new Exception("Browser not supported");
                }
            } else {
                throw new Exception("Operating System not supported");
            }
        } else {
            if (operatingSystem.contains("windows")) {
                switch (browser) {
                    case "chrome":
                        service = new ChromeDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/chromedriver.exe"))
                                .usingPort(Integer.parseInt(port))
                                .build();
                        service.start();
                        driver = new ChromeDriver((ChromeDriverService) service);
                        break;
                    case "firefox":
                        service = new GeckoDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/geckodriver.exe"))
                                .usingPort(Integer.parseInt(port))
                                .build();
                        service.start();
                        driver = new FirefoxDriver((GeckoDriverService) service);
                        break;
                    case "ie":
                        service = new InternetExplorerDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/IEDriverServer.exe"))
                                .usingPort(Integer.parseInt(port))
                                .build();
                        service.start();
                        driver = new InternetExplorerDriver((InternetExplorerDriverService) service);
                        break;
                    case "edge":
                        service = new EdgeDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/MicrosoftWebDriver.exe"))
                                .usingPort(Integer.parseInt(port))
                                .build();
                        service.start();
                        driver = new EdgeDriver((EdgeDriverService) service);
                        break;
                    default:
                        throw new Exception("Browser not supported");
                }
            } else if (operatingSystem.contains("linux")) {
                switch (browser) {
                    case "chrome":
                        service = new ChromeDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/chromedriver"))
                                .usingPort(Integer.parseInt(port))
                                .build();
                        service.start();
                        driver = new ChromeDriver((ChromeDriverService) service);
                        break;
                    case "firefox":
                        service = new GeckoDriverService.Builder()
                                .usingDriverExecutable(new File("drivers/geckodriver"))
                                .usingPort(Integer.parseInt(port))
                                .build();
                        service.start();
                        driver = new FirefoxDriver((GeckoDriverService) service);
                        break;
                    default:
                        throw new Exception("Browser not supported");
                }
            } else {
                throw new Exception("Operating System not supported");
            }
        }

        Thread.sleep(2000);

        int widthValue = (width.isEmpty()) ? 1024 : Integer.parseInt(width);
        int heightValue = (height.isEmpty()) ? 768 : Integer.parseInt(height);
        driver.manage().window().setSize(new Dimension(widthValue, heightValue));
    }

    public WebDriver getDriver() {
        return driver;
    }
}
