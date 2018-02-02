package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;


public class LandingPage extends EmagAbstractPage{

    @FindBy(xpath="//a[@class='back-button']")
    private WebElement backButton;

    public HomePage goToHomePage(){
        try {
            backButton.click();
        }
        catch(NoSuchElementException e){}
        return new HomePage();
    }

}
