package pageObjects;

import entityModel.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends EmagAbstractPage{

    @FindBy(xpath = "//div[@class='card']")
    private List<WebElement> productCards;

    @FindBy(xpath = "//a[@id='compare-link']")
    private WebElement compareButton;

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<Product>();
        for (int i=0;i<5;i++){
            WebElement productCard = productCards.get(i);
            Product product = new Product();
            product.setNewPrice(productCard.findElement(By.xpath(".//*[@class='product-new-price']")).getText());
            product.setOldPrice(productCard.findElement(By.xpath(".//*[@class='product-old-price']/s")).getText());
            product.setTitle(productCard.findElement(By.xpath(".//*[@class='product-title js-product-url']")).getText());
            products.add(product);
        }
        return products;
    }

    public Product getSpecificProduct(int i){
        int index = 0;
        List<Product> productCards = this.getProducts();
        for(Product product : productCards){
            if (!product.getOldPrice().trim().contentEquals("")) {
                index++;
            }
            if (index == i) {
                break;
            }
        }
        return productCards.get(index);
    }

    public void selectForComparing(int i){
        WebElement productCard = productCards.get(i);
        productCard.findElement(By.xpath(".//div[@class='card-toolbox']/button")).click();
    }

    public ProductCardPage openProductCard(int i){
        this.productCards.get(i).findElement(By.xpath(".//a[@class='thumbnail js-product-url']")).click();
        return new ProductCardPage();
    }

    public ComparePage openComparison(){
        compareButton.click();
        return new ComparePage();
    }
}
