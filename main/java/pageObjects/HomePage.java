package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends EmagAbstractPage {

    @FindBy(id = "searchboxTrigger")
    private WebElement category;

    @FindBy(xpath = "//button[@class='btn btn-default searchbox-submit-button']")
    private WebElement searchButton;

    public SearchResultsPage searchCategory(String searchString){
        this.category.sendKeys(searchString);
        this.searchButton.click();
        return new SearchResultsPage();
    }


}
