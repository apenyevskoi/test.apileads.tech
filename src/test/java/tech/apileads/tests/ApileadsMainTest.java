package tech.apileads.tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tech.apileads.Base;
import tech.apileads.pages.ApileadsMainPage;
import tech.apileads.TestListener;

/**
 * Test class to check input data on the page "https://test.apileads.tech/"
 * Allure report is available
 */

@ExtendWith(TestListener.class)
public class ApileadsMainTest extends Base {

    /**
     * Test to insert different combinations of data on the web-page forms
     * Test-cases 1-9
     * @param email form
     * @param name form
     * @param texts form
     * @param type drop-box
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/shortinputTestData.csv", delimiter = ';', emptyValue = "")
//    @CsvSource(value = {
//        "user@mail.ru, user, anytext, Offer",
//        "user@mail.ru, user#2, length > 1001 symbols, Sale",
//        "user@mail.ru, user, any text, Discount",
//        "usermail.ru, user, any text, Offer, errorMessage1",
//        "user>@mail.ru, user,any text, Sale, errorMessage2",
//        "user@, user, any text, Sale, errorMessage3"
//        "usermail.ru,user,'',Discount",
//        "'',user,'',Offer",
//        "'',user,any text,Sale",
//        "'','',any text,Discount"
//    })
    public void validateDataFormTest(String email, String name, String texts, String type, String expectedMessage){
        String actual;

        /* input data to the form of page */
        ApileadsMainPage apileadsMainPage = new ApileadsMainPage();
        apileadsMainPage.insertEmailData(email);
        apileadsMainPage.insertNameData(name);
        apileadsMainPage.insertTextsData(texts);
        apileadsMainPage.selectRecord(type);
        apileadsMainPage.clickLoginBtn();

        /**
         * Check whether the optional message is appeared through incorrect email.
         * Intercept attribute "validationMessage"
          */
        if (expectedMessage.contains("Адрес электронной почты должен содержать символ")) {
            actual = new WebDriverWait(driver, 7)
                        .until(ExpectedConditions
                                .elementToBeClickable(By.cssSelector("div.form-outline.mb-4 input#email")))
                        .getAttribute("validationMessage");
            Allure.step("Expected: " + expectedMessage + ". Actual: " + actual);
            Assertions.assertEquals(expectedMessage, actual);
        }
        if (expectedMessage.contains("Часть адреса до символа")) {
            actual = new WebDriverWait(driver, 7)
                    .until(ExpectedConditions
                            .elementToBeClickable(By.cssSelector("div.form-outline.mb-4 input#email")))
                    .getAttribute("validationMessage");
            Allure.step("Expected: " + expectedMessage + ". Actual: " + actual);
            Assertions.assertEquals(expectedMessage, actual);
        }
        if (expectedMessage.contains("Введите часть адреса после символа")) {
            actual = new WebDriverWait(driver, 7)
                    .until(ExpectedConditions
                            .elementToBeClickable(By.cssSelector("div.form-outline.mb-4 input#email")))
                    .getAttribute("validationMessage");
            Allure.step("Expected: " + expectedMessage + ". Actual: " + actual);
            Assertions.assertEquals(expectedMessage, actual);
        }
    }
}
//    @CsvSource(value = {
//        "user@mail.ru, user, anytext, Offer",
//        "user@mail.ru, user#2, length > 1001 symbols, Sale",
//        "user@mail.ru, user, any text, Discount",
//        "usermail.ru,'',any text,Offer",
//        "usermail.ru,user,any text,Sale",
//        "usermail.ru,user,'',Discount",
//        "'',user,'',Offer",
//        "'',user,any text,Sale",
//        "'','',any text,Discount"
//    })