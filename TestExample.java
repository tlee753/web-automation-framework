import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.*;
import util.*;


public class TestExample {

    private static WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "width", "height", "port", "url"})
    public void login(String browser, String width, String height, String port, String url) throws Exception {
        WAFWebDriver wafWebDriver = new WAFWebDriver(browser, width, height, port);
        driver = wafWebDriver.getDriver();
    }

    @Test
    public void testX_X_X() throws Exception {
        driver.get("https://google.com");
        Assert.assertEquals(driver.getTitle(), "Bing");
    }

    @Test
    public void testX_X_Y() throws Exception {
        driver.get("https://google.com");
        Assert.assertEquals(driver.getTitle(), "Google");
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}
