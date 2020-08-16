package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    private static WebDriver driver;

        public ProductPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver,this);
        }

        @FindBy(id = "group_1")
        public WebElement size;

        @FindBy(id ="quantity_wanted")
        private WebElement quantity;

        @FindBy(xpath ="//button[@class='btn btn-primary add-to-cart']")
        private WebElement addToCart;

        @FindBy(xpath ="//button[@class='btn btn-primary']")
         private WebElement addToCart2;


        public void sizeSelect() {
            //choose size M
            WebElement size = driver.findElement(By.id("group_1"));
            Select sizeSelect = new Select(size);
            sizeSelect.selectByVisibleText("M");

            Select quantityDropdown = new Select(quantity);
            quantityDropdown.selectByVisibleText("5");
        }

    public void setQuantity() {

        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys("5");
            }

    public void addToCart() {
        driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        }
    }
