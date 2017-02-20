package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Asia on 16.02.2017.
 */
public class GoogleSearchPage{

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(name = "btnG")
    private WebElement bntGoogleSearch;

    private final WebDriver driver;
    private static final String GOOGLE_TITLE = "Google";

    public GoogleSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        if (!GOOGLE_TITLE.equals(driver.getTitle())) {
            System.out.println(driver.getTitle());
            throw new IllegalStateException("This is not a Google Search Page.");
        }
    }

    public void enterSearchQuery(String searchQuery) {
        searchField.sendKeys(searchQuery);
    }

    public GoogleSearchPage submitSearch() {
        bntGoogleSearch.click();
        return new GoogleSearchPage(driver);
    }

}
