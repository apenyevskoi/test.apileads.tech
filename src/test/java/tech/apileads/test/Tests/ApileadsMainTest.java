package tech.apileads.test.Tests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tech.apileads.test.Base;
import tech.apileads.test.Pages.ApileadsMainPage;
import tech.apileads.test.TestListener;

/**
 * Test class to check input data on the page "https://test.apileads.tech/"
 * Allure report is available
 */

@ExtendWith(TestListener.class)
public class ApileadsMainTest extends Base {

    public String apileadsUrl = "https://test.apileads.tech/";

    /**
     * Test to insert different combinations of data on the web-page forms
     * Test-cases 1-9
     * @param email form
     * @param name form
     * @param texts form
     * @param type drop-box
     */
    @ParameterizedTest
    @CsvSource(value = {
        "user@mail.ru,user,anytext,Offer",
        "user@mail.ru,'','',Sale",
        "user@mail.ru, user, any text, Discount",
        "usermail.ru,'',any text,Offer"
        "usermail.ru,user,any text,Sale",
        "usermail.ru,user,'',Discount",
        "'',user,'',Offer",
        "'',user,any text,Sale",
        "'','',any text,Discount"
})
    public void insertDataCombinationsTest(String email, String name, String texts, String type){
        driver.get(apileadsUrl);
        ApileadsMainPage apileadsMainPage = new ApileadsMainPage();
        apileadsMainPage.insertEmailData(email);
        apileadsMainPage.insertNameData(name);
        apileadsMainPage.insertTextsData(texts);
        apileadsMainPage.selectRecord(type);
        apileadsMainPage.clickLoginBtn();
    }
}