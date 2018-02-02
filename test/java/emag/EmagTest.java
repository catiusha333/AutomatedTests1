package emag;

import entityModel.Product;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.*;

import java.util.Map;

public class EmagTest {


    private WebDriver webDriver;
    private Environment environment = Environment.getInstance();
    private final By veziCosButton = By.xpath("//a[@class='btn btn-primary btn-sm btn-block']");
    private final By cosTitle = By.xpath("//a[@class='line-item-title main-product-title']");
    private final By cosOldPrice = By.xpath("//div[@class='price-original']/span");
    private final By cosNewPrice = By.xpath("//div[@class='price-main']/span");
    private int index = 0;
    private String title;
    private String oldPrice;
    private String newPrice;

    //WebDriverWait wait = new WebDriverWait(webDriver,2);
    @Before
    public void init(){

        environment.startWebDriver("chrome", "https://www.emag.ro");
    }

    @After
    public void cleanup(){

        environment.getWebDriver().quit();
    }

    //@Test
    public void emagTest1() {
        int productNumber = 2;

        //LandingPage landingPage = new LandingPage();
        //SearchResultsPage result = landingPage.goToHomePage().searchCategory("smartwatch");

        // get result page
        HomePage homePage = new HomePage();
        SearchResultsPage result =  homePage.searchCategory("smartwatch");

        Product product = result.getSpecificProduct(productNumber);

        // open cart page
        CartPage cartPage = result.openProductCard(productNumber).addToCart().goToCart();
        Product cartProduct = cartPage.getProductFromCart(0);

        // Screenshot
        // Screenshots.captureScreenShot("pic");
        // check the number of products in cart
        Assert.assertThat(cartPage.countCartItems(), Is.is(1));

        // check the cart product details match the search page product details
        Assert.assertThat(product.getNewPrice(), Is.is(cartProduct.getNewPrice()));
        Assert.assertThat(product.getOldPrice(), Is.is(cartProduct.getOldPrice()));
        Assert.assertThat(product.getTitle(), Is.is(cartProduct.getTitle()));

        // display the product details from both pages: search and cart
        //Replace with logging (logback)
        System.out.println(product.getNewPrice() + " " + product.getOldPrice() + " " + product.getTitle());
        System.out.println(cartProduct.getNewPrice() + " " + cartProduct.getOldPrice() + " " + cartProduct.getTitle());

        // go back to search page
        cartPage.backToSearchResults();
    }

    //@Test
    public void emagTest2() {

        int firstProduct = 1;
        int secondProduct = 2;

        //LandingPage landingPage = new LandingPage();
        //SearchResultsPage result = landingPage.goToHomePage().searchCategory("smartwatch");

        HomePage homePage = new HomePage();
        SearchResultsPage result = homePage.searchCategory("smartwatch");

        Product product1 = result.getSpecificProduct(firstProduct);
        Product product2 = result.getSpecificProduct(secondProduct);

        result.selectForComparing(firstProduct);
        result.selectForComparing(secondProduct);

        ComparePage comparePage = result.openComparison();

        Assert.assertThat(product1.getTitle(), Is.is(comparePage.getProductDetails(0).getTitle()));
        Assert.assertThat(product1.getOldPrice(), Is.is(comparePage.getProductDetails(0).getOldPrice()));
        Assert.assertThat(product1.getNewPrice(),Is.is(comparePage.getProductDetails(0).getNewPrice()));

        Assert.assertThat(product2.getTitle(), Is.is(comparePage.getProductDetails(1).getTitle()));
        Assert.assertThat(product2.getOldPrice(), Is.is(comparePage.getProductDetails(1).getOldPrice()));
        Assert.assertThat(product2.getNewPrice(), Is.is(comparePage.getProductDetails(1).getNewPrice()));
        comparePage.backToSearchResults();
    }

    @Test
    public void emagTest3() {

        int firstProduct = 1;
        int secondProduct = 2;

        //LandingPage landingPage = new LandingPage();
        //SearchResultsPage result = landingPage.goToHomePage().searchCategory("smartwatch");

        HomePage homePage = new HomePage();
        SearchResultsPage result = homePage.searchCategory("smartwatch");

        ProductCardPage productCard1 = result.openProductCard(firstProduct);
        Map m1 = productCard1.getTechnicalCategory();
        productCard1.backToSearchResults();

        ProductCardPage productCard2 = result.openProductCard(secondProduct);
        Map m2 = productCard2.getTechnicalCategory();
        productCard2.backToSearchResults();

        result.selectForComparing(firstProduct);
        result.selectForComparing(secondProduct);
        ComparePage comparePage = result.openComparison();

        Map mc1 = comparePage.getProductSpecs(firstProduct);
        Map mc2 = comparePage.getProductSpecs(secondProduct);
        comparePage.backToSearchResults();

        System.out.println();
        System.out.println(" Map Elements");
        System.out.print("\t" + mc1);

        System.out.println();
        System.out.println(" Map Elements");
        System.out.print("\t" + mc2);

        Assert.assertThat(m1, Is.is(mc1));
        Assert.assertThat(m2, Is.is(mc2));
    }
}
