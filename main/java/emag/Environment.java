package emag;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Environment {

    private WebDriver webDriver;

    private static Environment instance;

    public static synchronized Environment getInstance(){
        if(instance == null){
            instance = new Environment();
        }
        return instance;
    }

    private Environment(){}

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebDriver startWebDriver(String browser, String url){
        if(browser.equals("firefox")){
            webDriver = new FirefoxDriver();
        } else if(browser.equals("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("-incognito");
            options.addArguments("--disable-popup-blocking");
            webDriver = new ChromeDriver();
        }
        webDriver.navigate().to(url);
        webDriver.manage().window().maximize();
        return webDriver;
    }


}
