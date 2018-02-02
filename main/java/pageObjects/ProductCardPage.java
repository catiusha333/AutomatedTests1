package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCardPage extends EmagAbstractPage{

    @FindBy(xpath = ".//p[@class='product-old-price']/s")
    private WebElement oldPrice;

    @FindBy(xpath = "//p[@class='product-new-price']")
    private WebElement newPrice;

    @FindBy(xpath = "//a[@class='product-title js-product-url']")
    private WebElement title;

    @FindBy(xpath = "//i[@class='em em-cart_fill gtm_680klw']/..")
    private WebElement addToCart;

    @FindBy(xpath = "//a[contains(text(),'Vezi detalii cos')]")
    private WebElement veziDetaliiCos;

    @FindBy(xpath = "//table[@class='table table-striped product-page-specifications']//tr")
    private List<WebElement> specificationCategory;

    public String gettOldPrice(){
        return oldPrice.getText();
    }

    public String gettNewPrice(){
        return newPrice.getText();
    }

    public String getTitle(){
        return title.getText();
    }

    public ProductCardPage addToCart() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollBy(0,400)");
        addToCart.click();
        return this;
    }

    public CartPage goToCart(){
        veziDetaliiCos.click();
        return new CartPage();
    }

    public Map getTechnicalCategory(){
        Map m = new HashMap();
        for(WebElement category:specificationCategory){
            m.put(category.findElement(By.xpath(".//td[1]")).getText(), category.findElement(By.xpath(".//td[2]")).getText());
        }
        return m;
    }
}
