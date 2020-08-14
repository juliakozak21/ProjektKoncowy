package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AddressPage {
    private static WebDriver driver;

    public AddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "alias")
    private WebElement aliasnew;

    @FindBy(name = "address1")
    private WebElement addressnew;

    @FindBy(name = "postcode")
    private WebElement postcodenew;

    @FindBy(name = "city")
    private WebElement citynew;

    @FindBy(xpath = ("//select[@class='form-control form-control-select js-country']"))
    private WebElement countrynew;

    @FindBy(xpath = "//button[@class='btn btn-primary float-xs-right']")
    private WebElement submit;

    public void SetAddress(String alias, String address, String postcode, String city, String country) {

        addressnew.click();
        addressnew.clear();
        addressnew.sendKeys(address);

        aliasnew.click();
        aliasnew.clear();
        aliasnew.sendKeys(address);

        citynew.click();
        citynew.clear();
        citynew.sendKeys(city);

        postcodenew.click();
        postcodenew.clear();
        postcodenew.sendKeys(postcode);


        Select countryDropdown = new Select(countrynew);
        countryDropdown.selectByVisibleText("United Kingdom");

        submit.click();

    }

    public void saveNewAddress() {
        if (submit.isDisplayed()) {
            submit.click();
        } else Assert.fail();
    }

    @FindBy(css = "article.address")
    private List<WebElement> address;

    public String getLastAddress(String alias) {
        return address.get(address.size() - 1).getText();
    }
}

