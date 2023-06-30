package tech.apileads.pages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tech.apileads.Base;

import java.util.Arrays;
import java.util.List;

import static tech.apileads.Base.driver;

/**
 * Page Class of "https://test.apileads.tech/"
 */

public class ApileadsMainPage{

    @FindBy(css = "div.form-outline.mb-4 input#email")
    private WebElement inputEmail;

    @FindBy(css = "div.form-outline.mb-4 input#names")
    private WebElement inputName;

    @FindBy(css = "div.form-outline.mb-4 input#message_text")
    private WebElement inputTexts;

    @FindBy(css = "div.form-outline.mb-4 select#type")
    private WebElement selectType;

    @FindBy(css = "button.btn")
    private WebElement loginBtn;

    private Select dropDown;

    /**
     * Constructor of tech.apileads.test.Pages.ApileadsMainPage
     */
    public ApileadsMainPage(){
        PageFactory.initElements(driver, this);
        dropDown = new Select(selectType);
    }

    /**
     * Insert "email" data into input form
     */
    public String insertEmailData(String email, String expectedMessage){
        String actual;
        Assertions.assertTrue(inputEmail.isDisplayed());
        Assertions.assertTrue(inputEmail.isEnabled());
        inputEmail.sendKeys(email);

        /**
         * Intercept attribute "validationMessage" in "Email" form if incorrect email was inputted
         */
        if (expectedMessage.contains("Адрес электронной почты должен содержать символ")) {
            actual = new WebDriverWait(driver, 7)
                    .until(ExpectedConditions
                            .elementToBeClickable(By.cssSelector("div.form-outline.mb-4 input#email")))
                    .getAttribute("validationMessage");
            return actual;
        }
        if (expectedMessage.contains("Часть адреса до символа")) {
            actual = new WebDriverWait(driver, 7)
                    .until(ExpectedConditions
                            .elementToBeClickable(By.cssSelector("div.form-outline.mb-4 input#email")))
                    .getAttribute("validationMessage");
            return actual;
        }
        if (expectedMessage.contains("Введите часть адреса после символа")) {
            actual = new WebDriverWait(driver, 7)
                    .until(ExpectedConditions
                            .elementToBeClickable(By.cssSelector("div.form-outline.mb-4 input#email")))
                    .getAttribute("validationMessage");
            return actual;
        }
        return "";
    }

    /**
     * Insert "name" data into input form
     */
    public String insertNameData(String name){
        Assertions.assertTrue(inputName.isDisplayed());
        Assertions.assertTrue(inputName.isEnabled());
        inputName.sendKeys(name);
        return inputName.getAttribute("value");
    }

    /**
     * Insert "text" data into input form
     */
    public String insertTextsData(String texts){
        Assertions.assertTrue(inputTexts.isDisplayed());
        Assertions.assertTrue(inputTexts.isEnabled());
        inputTexts.sendKeys(texts);
        return inputTexts.getAttribute("value");
    }

    /**
     * Select record in drop-box form
     */
    public void selectRecord(String type){
        Assertions.assertTrue(selectType.isDisplayed());
        Assertions.assertTrue(selectType.isEnabled());
        try {
            List<WebElement> dropDownList = dropDown.getAllSelectedOptions();
            Assertions.assertFalse(dropDownList.isEmpty());
            List<String> expectedDropDownOptionsList = Arrays.asList("Offer", "Sale", "Discount");
            for (int i = 0; i < dropDownList.size(); i++) {
                Assertions.assertTrue(dropDownList.get(i).getText().equals(expectedDropDownOptionsList.get(i)));
            }
        }catch (Exception e){
        }
        if (type.equals("Offer"))
            dropDown.selectByIndex(0);
        if (type.equals("Sale"))
            dropDown.selectByIndex(1);
        if (type.equals("Discount"))
            dropDown.selectByIndex(2);
    }

    /**
     * Click login button
     */
    public void clickLoginBtn(){
        Assertions.assertTrue(loginBtn.isDisplayed());
        Assertions.assertTrue(loginBtn.isEnabled());
        loginBtn.click();
    }
}
