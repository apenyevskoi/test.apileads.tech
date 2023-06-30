package tech.apileads.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tech.apileads.Base;

import java.util.Arrays;
import java.util.List;

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
        PageFactory.initElements(Base.driver, this);
        dropDown = new Select(selectType);
    }

    /**
     * Insert "email" data into input form
     */
    public void insertEmailData(String email){
        Assertions.assertTrue(inputEmail.isDisplayed());
        inputEmail.sendKeys(email);
    }

    /**
     * Insert "name" data into input form
     */
    public void insertNameData(String name){
        Assertions.assertTrue(inputName.isDisplayed());
        inputName.sendKeys(name);
    }

    /**
     * Insert "text" data into input form
     */
    public void insertTextsData(String texts){
        Assertions.assertTrue(inputTexts.isDisplayed());
        inputTexts.sendKeys(texts);
    }

    /**
     * Select record in drop-box form
     */
    public void selectRecord(String type){
        try {
            List<WebElement> dropDownList = dropDown.getAllSelectedOptions();
            List<String> shouldBeList = Arrays.asList("Offer", "Sale", "Discount");
            for (int i = 0; i < dropDownList.size(); i++) {
                Assertions.assertTrue(dropDownList.get(i).getText().equals(shouldBeList.get(i)));
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
