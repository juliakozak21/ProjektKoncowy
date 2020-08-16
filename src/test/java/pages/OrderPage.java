package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrderPage {
    private static WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "group_1")
    private WebElement size;

    @FindBy(id = "quantity_wanted")
    private WebElement quantity;

    @FindBy(xpath ="//*[@id=\"add-to-cart-or-refresh\"]")
    private WebElement addtocart;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    private WebElement proceedToCheckout;

    @FindBy(xpath = "//input[@name='id_address_delivery']")
    private WebElement address;

    @FindBy(xpath = "//section[@id=\'checkout-delivery-step\']")
    private WebElement shippingMethod;

    @FindBy(xpath = ("//input[@id='delivery_option_1']"))
    private WebElement selfpickup;

    @FindBy(xpath = ("//section[@id=\"checkout-payment-step\"]"))
    private WebElement payment;

    @FindBy(xpath = ("//input[@id='payment-option-1']"))
    private WebElement bycheck;

    @FindBy(xpath = ("//input[@id=\"conditions_to_approve[terms-and-conditions]\"]"))
    private WebElement terms;

    @FindBy(xpath = ("//button[@class=\"btn btn-primary center-block\"]"))
    private WebElement buy;

    public void proceedToCheckout() {
        if (proceedToCheckout.isEnabled()) {
            proceedToCheckout.click();
        } else throw new AssertionError("Couldn't proceed to checkout");
    }

    public void confirmsAddress() {
        if (address.isSelected()) {
            address.click();
        }
    }

    public void addsDeliveryOption() {
        shippingMethod.click();
        selfpickup.click();
    }

    public void bycheck() {
        payment.click();
        if (bycheck.isSelected()) {
            bycheck.click();
        }
    }

    public void TermsandConditions() {
        if (terms.isSelected()) {
            terms.click();
        }

        buy.click();
    }

    }

