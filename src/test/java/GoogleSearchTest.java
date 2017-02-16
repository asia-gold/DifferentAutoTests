import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Asia on 05.02.2017.
 */
public class GoogleSearchTest {

    private WebDriver driver;
    private String baseUrl;
    private GoogleSearchPage google;
    WebDriverWait wait;
    private static final String SEARCH_QUERY = "Jason Becker";

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        baseUrl = "https://www.google.com.ua/";
        driver.get(baseUrl + "?gws_rd=ssl");
        google = new GoogleSearchPage(driver);
        wait = new WebDriverWait(driver, 7);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() throws Exception {
        System.out.println(driver.getTitle());
        makeSearchWithGoogle(SEARCH_QUERY);
        waitForSearchResults();
        assertEquals(SEARCH_QUERY + " - Поиск в Google", driver.getTitle());
    }

    private void makeSearchWithGoogle(String searchQuery) {
        //driver.findElement(By.name("q")).sendKeys(searchQuery);
        //driver.findElement(By.name("btnG")).click();
        google.enterSearchQuery(searchQuery);
        google.submitSearch();
    }

    private void waitForSearchResults() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
    }
}
