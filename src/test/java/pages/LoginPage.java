package pages;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    private static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        //open browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //wait 10s to find element
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //go to login page
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
    }

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    private WebElement loginInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit-login")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='account']")
    WebElement userName;

    public void loginAs(String email, String password) {
        loginInput.click();
        loginInput.clear();
        loginInput.sendKeys(email);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        signInButton.click();
    }

    public String getLoggedUsername() {
        return userName.getText();
    }
    public void goToLoginPage() {
        if (userName.isDisplayed()) {
            userName.click();
        } else throw new AssertionError("Couldn't log in");
    }
}

