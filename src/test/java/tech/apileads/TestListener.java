package tech.apileads;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Descriptions of actions during "Failed" and "Success" tests
 */

public class TestListener implements TestWatcher {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(WebDriver.class);

    /**
     * Description of actions during "Failed" test. Provides report and screenshot to allure
     * @param context
     * @param cause
     */

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            Allure.getLifecycle().addAttachment("Скриншот провального теста", "image/png", "png",
                    ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.BYTES));
            Allure.addAttachment("Screenshot is done",
                    String.valueOf(Base.driver.manage().logs().get(LogType.BROWSER).getAll()));
            Allure.step("Failure on " + context.getTestMethod());
        }catch (Exception e) {
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            //e.getClass().getName();
            e.printStackTrace();
        }finally {
            WebDriverManager.chromedriver().quit();
            Base.driver.close();
        }
    }

    /**
     * Description of actions during "Success". Provides allure report of successful test
     * @param context
     */

    @Override
    public void testSuccessful(ExtensionContext context) {
        try {
            Allure.step("Success on " + context.getRequiredTestMethod().getName());
        }catch (Exception e) {
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            //e.getClass().getName();
            e.printStackTrace();
        }finally {
            WebDriverManager.chromedriver().quit();
            Base.driver.close();
        }
    }
}
