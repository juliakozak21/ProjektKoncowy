package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.OrderPage;
import org.openqa.selenium.TakesScreenshot;
import pages.ProductPage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;



public class OrderSteps {
    private WebDriver driver;

    private OrderPage orderPage;
    private ProductPage productPage;


    @Given("^user is logged in$")
    public void userIsLoggedIn() {
        // Skonfiguruj sterownik przeglądarki
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Log in
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("test+1@gmail.com", "robo1");
        Assert.assertEquals("Robo Bobo", loginPage.getLoggedUsername());
    }

    @And("^is on HUMMINGBIRD PRINTED SWEATER product page$")
    public void isOnHUMMINGBIRDPRINTEDSWEATERProductPage() {
        driver.get("https://prod-kurs.coderslab.pl/index.php?id_product=2&id_product_attribute=10&rewrite=brown-bear-printed-sweater&controller=product#/2-size-s");
    }

    @When("^chooses size \"([^\"]*)\"$")
    public void choosesSize(String size) {
        ProductPage productPage = new ProductPage(driver);
        productPage.sizeSelect();
    }

    @And("^adds items \"([^\"]*)\"$")
    public void addsItems() {
        ProductPage productPage = new ProductPage(driver);
        productPage.setQuantity();

    }

    @And("^adds to basket$")
    public void addsToBasket() {
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=cart&action=show");

        productPage.addToCart();
    }

    @Then("^can proceed to checkout$")
    public void canProceedToCheckout() {
        OrderPage checkout = new OrderPage(driver);
        checkout.proceedToCheckout();
    }

    @And("^confirms address$")
    public void confirmsAddress() {
        OrderPage address = new OrderPage(driver);
        address.confirmsAddress();

    }

    @And("^adds delivery option \"([^\"]*)\"$")
    public void addsDeliveryOption() {
        OrderPage delivery = new OrderPage(driver);
        delivery.addsDeliveryOption();
    }

    @And("^Payment method$")
    public void paymentMethod() {

        OrderPage newpayment = new OrderPage(driver);
        newpayment.bycheck();
    }

    @And("^clicks \"([^\"]*)\"$")
    public void clicks() {
        OrderPage terms = new OrderPage(driver);
        terms.TermsandConditions();
    }

    @And("^makes a screenshot$")
    public void makesAScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String filename = now.format(formatter) + "-screenshot.png";
        try {
            FileUtils.copyFile(scrFile, new File("screenshots/" + filename));
        } catch (IOException e) {
            System.out.println("Screenshot failed!!!");
            e.printStackTrace();

        }
    }
}