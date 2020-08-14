package stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.AddressPage;

import java.util.concurrent.TimeUnit;


public class LoginSteps {
    private WebDriver driver;

    private LoginPage loginPage;
    private AddressPage address;

    @Given("^user is logged in the store$")
    public void userIsLoggedInTheStore() {
        // Skonfiguruj sterownik przeglądarki
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        // Uruchom nowy egzemplarz przeglądarki Chrome
        driver = new ChromeDriver();
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        //wait 10s to find element
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Log in
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("test+1@gmail.com", "robo1");
        Assert.assertEquals("Robo Bobo", loginPage.getLoggedUsername());

    }

    @And("^user is on Addresses page$")
    public void userIsOnAddressesPage() {
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=address");
    }

    @When("^user adds new address \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userAddsNewAddress(String alias, String address, String postcode, String city, String country) throws Throwable {
        //user enters New address
        AddressPage addressPage = new AddressPage(driver);
        addressPage.SetAddress(alias, address, postcode, city, country);

    }

    @Then("^address has been added \"([^\"]*)\"$")
    public void addressHasBeenAdded ()  throws Throwable {
        // user saves address
        AddressPage saveaddressPage = new AddressPage(driver);
        saveaddressPage.saveNewAddress();
        throw new PendingException();
    }

    @And("^checks if data is true \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void checksIfDataIsTrue(String alias, String address, String postcode, String city, String country) throws Throwable {
        //assert the details of added address are correct
        AddressPage addressPage = new AddressPage(driver);
        String costam = alias + "\n" +
                "Robo bobo \n" +
                address + "\n" +
                city + "\n" +
                postcode + "\n" +
                country + "\n" +
                "\uE254 Update \uE872 Delete";
        Assert.assertEquals(costam, addressPage.getLastAddress(alias));

    }
}

    