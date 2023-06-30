package tech.apileads.tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
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
     * Test to insert different "Email" data at the web-page forms
     * Test-cases 1-9
     * @param email form
     * @param name form
     * @param texts form
     * @param type drop-box
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/inputTestEmailData.csv", delimiter = ';', emptyValue = "")
//    @Disabled
    public void validateEmailDataFormTest(String email, String name, String texts, String type, String expectedMessage){
        ApileadsMainPage apileadsMainPage = new ApileadsMainPage();

        /* input data to the form of page. Get back "actual" from "Email" form */
        String actualEmailMassage = apileadsMainPage.insertEmailData(email, expectedMessage);
        apileadsMainPage.insertNameData(name);
        apileadsMainPage.insertTextsData(texts);
        apileadsMainPage.selectRecord(type);
        apileadsMainPage.clickLoginBtn();

        /**
         * Check whether the optional message is appeared through incorrect "email".
         */
        if(!actualEmailMassage.isEmpty()) {
            Allure.step("Expected message: " + expectedMessage + ". Actual message: " + actualEmailMassage);
            Assertions.assertEquals(expectedMessage, actualEmailMassage);
        }
    }

    /**
     * Test to insert different "Name" data at the web-page forms
     * Test-cases 1-9
     * @param email form
     * @param name form
     * @param texts form
     * @param type drop-box
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/inputTestNameData.csv", delimiter = ';', emptyValue = "")
//    @Disabled
    public void validateNameDataFormTest(String email, String name, String texts, String type, String expectedMessage){
        ApileadsMainPage apileadsMainPage = new ApileadsMainPage();

        /* input data to the form of page. Get back "actual" from "Name" form*/
        apileadsMainPage.insertEmailData(email, expectedMessage);
        String actualName = apileadsMainPage.insertNameData(name);
        apileadsMainPage.insertTextsData(texts);
        apileadsMainPage.selectRecord(type);
        apileadsMainPage.clickLoginBtn();

        /**
         * Check whether actual "name" is valid, correct length and not empty
         */
        Assertions.assertTrue(!actualName.isEmpty());
        Assertions.assertTrue(actualName.matches("^[a-zA-Z0-9]+$"));
        Assertions.assertTrue(actualName.length() < 256);
    }

    /**
     * Test to insert data to "Texts" form at the web-page forms
     * Test-cases 1-9
     * @param email form
     * @param name form
     * @param texts form
     * @param type drop-box
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/inputTestTextsData.csv", delimiter = ';', emptyValue = "")
//    @Disabled
    public void validateTextsDataFormTest(String email, String name, String texts, String type, String expectedMessage){
        ApileadsMainPage apileadsMainPage = new ApileadsMainPage();

        /* input data to the form of page. Get back "actual" from "Texts" form */
        apileadsMainPage.insertEmailData(email, expectedMessage);
        apileadsMainPage.insertNameData(name);
        String actualTexts = apileadsMainPage.insertTextsData(texts);
        apileadsMainPage.selectRecord(type);
        apileadsMainPage.clickLoginBtn();

        /**
         * Check whether actual "texts" is correct length and not empty
         */
        Assertions.assertTrue(!actualTexts.isEmpty());
        Assertions.assertTrue(actualTexts.length() < 1000);
    }
}
