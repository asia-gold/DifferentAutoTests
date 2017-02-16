import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Asia on 06.02.2017.
 */
public class SwitchingBetweenTabsTest {

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private String appleTab;
    private WebDriverWait webDriverWait;

    public void setAppleTab(String appleTab) {
        this.appleTab = appleTab;
    }

    public String getAppleTab() {
        return appleTab;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        javascriptExecutor = (JavascriptExecutor)driver;
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }
//    @Ignore("Jenkins doesn't know Russian")
    @Test
    public void testSwitchingBetweenTabs() {
        openApple();
        waitForAppleToLoad();
        assertEquals("Apple", driver.getTitle());
        openSamsung();
        waitForSamsungToLoad();
        assertEquals("Samsung Украина | Мобильные устройства| Телевизоры | Бытовая техника |", driver.getTitle());
        switchBackToApple();
    }

    public void openApple() {
        driver.get("http://www.apple.com/");
        setAppleTab(driver.getWindowHandle());
        System.out.println(driver.getTitle());
    }

    public void waitForAppleToLoad() {
        webDriverWait = new WebDriverWait(driver,10);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='homepage-gallery-watch']/figure")));
    }

    public void openSamsung() {
        javascriptExecutor.executeScript("window.open('http://www.samsung.com/');");
        System.out.println(driver.getTitle());
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(getAppleTab());
        driver.switchTo().window(newTab.get(0));
        System.out.println(driver.getTitle());
    }

    public void waitForSamsungToLoad() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-btn-cluster")));
    }

    public void switchBackToApple() {
        driver.close();
        driver.switchTo().window(appleTab);
    }
}
