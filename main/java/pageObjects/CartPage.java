package pageObjects;

import entityModel.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends EmagAbstractPage {

    @FindBy(xpath = "//div[@class='cart-vendor-container']")
    private List<WebElement> cartContainers;

    public int countCartItems() {
        return cartContainers.size();
    }

    public Product getProductFromCart(int i){
            WebElement productContainer = cartContainers.get(i);
            Product product = new Product();
            product.setTitle(productContainer.findElement(By.xpath(".//a[@class='line-item-title main-product-title']")).getText());
            product.setNewPrice(productContainer.findElement(By.xpath(".//div[@class='price-main']")).getText());
            product.setOldPrice(productContainer.findElement(By.xpath(".//div[@class='price-original']")).getText());
            return product;
    }
}

