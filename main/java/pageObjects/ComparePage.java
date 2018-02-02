package pageObjects;

import entityModel.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComparePage extends EmagAbstractPage {

    @FindBy(xpath = "//th[@class='table-compare-column']")
    private List<WebElement> productCards;

    @FindBy(xpath = "//tr[@class='table-compare-diff']")
    private List<WebElement> specsLines;

    public Product getProductDetails(int i){
        WebElement container = productCards.get(i);
        Product product = new Product();
        product.setTitle(container.findElement(By.xpath(".//a[@class='product-title']")).getText());
        product.setOldPrice(container.findElement(By.xpath(".//p[@class='product-old-price']/s")).getText());
        product.setNewPrice(container.findElement(By.xpath(".//p[@class='product-new-price']")).getText().trim());
        return product;
    }

    public Map getProductSpecs(int i){
        Map m = new HashMap();
        i++;
        for(WebElement specsLine:specsLines){
            if(!specsLine.findElement(By.xpath(".//td[" + i + "]")).getText().trim().contentEquals(""))
            m.put(specsLine.findElement(By.xpath(".//td[1]")).getText(), specsLine.findElement(By.xpath(".//td[" + i + "]")).getText());
        }
        return m;
    }
}
