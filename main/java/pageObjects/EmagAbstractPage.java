package pageObjects;

import emag.Environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public abstract class EmagAbstractPage {

    protected WebDriver webDriver;

    public EmagAbstractPage(){
        webDriver = Environment.getInstance().getWebDriver();
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 15), this);
    }

    public SearchResultsPage backToSearchResults(){
        webDriver.navigate().back();
        return new SearchResultsPage();
    }

}
