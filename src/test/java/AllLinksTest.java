import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Asia on 20.02.2017.
 */
public class AllLinksTest {

    private WebDriver driver;
    private String baseUrl;
    private static final String NOT_READY = "Under Construction: Mercury Tours";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        baseUrl = "http://newtours.demoaut.com/";
        driver.get(baseUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testAllLinksOnThePage() {
        List<WebElement> linkElements = driver.findElements(By.tagName("a"));
        String[] linkTexts = new String[linkElements.size()];
        int i = 0;
        for(WebElement element : linkElements) {
            linkTexts[i] = element.getText();
            i++;
        }
        for(String text : linkTexts) {
            driver.findElement(By.linkText(text)).click();
            if(driver.getTitle().equals(NOT_READY)) {
                System.out.println("!!!" + text + ": is NOT working");
            } else {
                if(driver.getTitle().contains("404")) {
                    System.out.println("!!!" + text + ": page NOT FOUND");
                } else {
                    System.out.println(text + ": is working");
                }
            }
            driver.navigate().back();
        }
    }
}
