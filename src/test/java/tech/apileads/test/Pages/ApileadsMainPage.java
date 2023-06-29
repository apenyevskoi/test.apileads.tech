package tech.apileads.test.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import tech.apileads.test.Base;

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

    /**
     * Constructor of tech.apileads.test.Pages.ApileadsMainPage
     */
    public ApileadsMainPage(){
        PageFactory.initElements(Base.driver, this);
    }

    /**
     * Insert email data into input form
     */
    public void insertEmailData(String email){
        inputEmail.sendKeys(email);
    }

    /**
     * Insert name data into input form
     */
    public void insertNameData(String name){
        inputName.sendKeys(name);
    }

    /**
     * Insert text data into input form
     */
    public void insertTextsData(String texts){
        inputTexts.sendKeys(texts);
    }

    /**
     * Select recort in drop-box form
     */
    public void selectRecord(String type){
        Select dropDown = new Select(selectType);
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
        loginBtn.click();
    }
}
