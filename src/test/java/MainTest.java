import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class MainTest {

    WebDriver driver;
    ExtentTest test;
    ExtentReports extentReports = new ExtentReports();

    String option1Xpath = "//*[@id=\"i5\"]";
    String option2Xpath = "//*[@id=\"i8\"]";
    String option3Xpath = "//*[@id=\"i11\"]";
    String option4Xpath = "//*[@id=\"i14\"]";
    String option5Xpath = "//*[@id=\"i17\"]";
    String optionOtherXpath = "//*[@id=\"i20\"]";
    String optionOtherTextXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div/div[2]/div[1]/div/span/div/div[6]/div/span/div/div/div[1]/input";
    String clearOptionXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[1]/div/div/div[2]/div[2]/div/div/span";

    String nameFieldXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/input";
    String emailFieldXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[1]/input";
    String addressFieldXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[4]/div/div/div[2]/div/div[1]/div[2]/textarea";
    String phoneNrFieldXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[1]/div/div[1]/input";
    String commentsFieldXpath = "//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[6]/div/div/div[2]/div/div[1]/div[2]/textarea";
    String emailTitleXpath = "//*[@id=\"i27\"]/span[1]";

    String nameInputErrorXpath = "//*[@id=\"i25\"]/span";
    String emailInputErrorXpath = "//*[@id=\"i29\"]/span";
    String addressInputErrorXpath = "//*[@id=\"i33\"]/span";

    String errorMessage = "See on kohustuslik küsimus";
    String emailInputErrorMessage = "Please enter a valid email address";
    String successfulApplicationText = "Thanks for submitting your contact info!";

    String confirmButtonXpath = "//span[contains(text(),'Saada ära')]";
    String submitScreenText = "/html/body/div[1]/div[2]/div[1]/div/div[3]";
    String submitScreenRedoButton = "/html/body/div[1]/div[2]/div[1]/div/div[4]/a";

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform");
        Thread.sleep(2000);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/Spark.html");
        extentReports.attachReporter(sparkReporter);
    }

    @AfterClass
    public void closeDown() {
        driver.quit();
    }

    @AfterMethod
    public void reopenWebsite() throws InterruptedException {
        driver.quit();
        driver = new ChromeDriver();
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform");
        Thread.sleep(2000);
    }

    @Test
    public void selectAllOptionsOneByOneTest() {
        test = extentReports.createTest("selectAllOptionsOneByOneTest", "Click thorugh all options");

        try {
            WebElement option1 = driver.findElement(By.xpath(option1Xpath));
            option1.click();
            test.log(Status.PASS, "Option 1 was clickable.");

            WebElement option2 = driver.findElement(By.xpath(option2Xpath));
            option2.click();
            test.log(Status.PASS, "Option 2 was clickable.");

            WebElement option3 = driver.findElement(By.xpath(option3Xpath));
            option3.click();
            test.log(Status.PASS, "Option 3 was clickable.");

            WebElement option4 = driver.findElement(By.xpath(option4Xpath));
            option4.click();
            test.log(Status.PASS, "Option 4 was clickable.");

            option4.click();
            test.log(Status.PASS, "Option 4 was clickable for 2nd time.");

            WebElement option5 = driver.findElement(By.xpath(option5Xpath));
            option5.click();
            test.log(Status.PASS, "Option 5 was clickable.");

            WebElement optionOther = driver.findElement(By.xpath(optionOtherXpath));
            optionOther.click();
            test.log(Status.PASS, "Option 'Muu' was clickable.");

            option4.click();
            test.log(Status.PASS, "Clicked Option 4 again.");

            WebElement optionOtherText = driver.findElement(By.xpath(optionOtherTextXpath));
            optionOtherText.sendKeys("Completely random test 123 ' == ' @£@$äš@$@ logincredentials");
            test.log(Status.PASS, "Option 'Muu' textfield was writable.");

            test.log(Status.PASS, "All options were interactable.");

            driver.findElement(By.xpath(confirmButtonXpath)).click();

        } catch (NoSuchElementException noSuchElementException) {
            test.log(Status.FAIL, "Invalid xpath: " + noSuchElementException);
            Assert.fail("Invalid xpath: " + noSuchElementException);
        } finally {
            extentReports.flush();
        }
    }

    @Test
    public void clearOptionSelectionTest() {
        test = extentReports.createTest("clearOptionSelectionTest", "Test 'Tühista valik' button action");

        try {
            WebElement option3 = driver.findElement(By.xpath(option3Xpath));
            option3.click();
            test.log(Status.PASS, "Option 3 was clickable.");

            assertTrue(Boolean.parseBoolean(option3.getAttribute("aria-checked")));
            test.log(Status.PASS, "Option 3 selection value is true.");

            WebElement clearButton = driver.findElement(By.xpath(clearOptionXpath));
            clearButton.click();
            test.log(Status.PASS, "Button 'Tühista valik' was clickable.");

            option3 = driver.findElement(By.xpath(option3Xpath));
            assertFalse(Boolean.parseBoolean(option3.getAttribute("aria-checked")));
            test.log(Status.PASS, "Option 3 selection value is false.");

        } catch (NoSuchElementException noSuchElementException) {
            test.log(Status.FAIL, "Invalid xpath: " + noSuchElementException);
            Assert.fail("Invalid xpath: " + noSuchElementException);
        } finally {
            extentReports.flush();
        }
    }

    @Test
    public void input1000CharactersTest() {
        test = extentReports.createTest("input1000CharactersTest", "Input 1000 characters. With 10000 characters this test took already over 1 minute for me.");

        try {
            WebElement textField = driver.findElement(By.xpath(nameFieldXpath));
            textField.sendKeys("a".repeat(1000));
            test.log(Status.PASS, "Entered 1000 characters successfully.");

            driver.findElement(By.xpath(confirmButtonXpath)).click();
            test.log(Status.PASS, "Pressing 'Saada ära' succeeded.");
        } catch (AssertionError assertionError) {
            test.log(Status.FAIL, "assert value was incorrect.");
            Assert.fail("assert value was incorrect.");
        } catch (NoSuchElementException noSuchElementException) {
            test.log(Status.FAIL, "Invalid xpath: " + noSuchElementException);
            Assert.fail("Invalid xpath: " + noSuchElementException);
        } finally {
            extentReports.flush();
        }
    }

    @Test
    public void noNameTest() {
        test = extentReports.createTest("noNameTest", "Leave 'Name' field empty");
        noTextInput(nameFieldXpath, "Name", nameInputErrorXpath);
    }

    @Test
    public void noEmailTest() {
        test = extentReports.createTest("noEmailTest", "Leave 'Email' field empty");
        noTextInput(emailFieldXpath, "Email", emailInputErrorXpath);
    }

    @Test
    public void noAddressTest() {
        test = extentReports.createTest("noAddressTest", "Leave 'Address' field empty");
        noTextInput(addressFieldXpath, "Address", addressInputErrorXpath);
    }

    @Test
    public void invalidEmailAddress() {
        test = extentReports.createTest("invalidEmailAddress", "Enter invalid email into 'Email' textfield");

        try {
            WebElement textField = driver.findElement(By.xpath(emailFieldXpath));
            WebElement emailTitle = driver.findElement(By.xpath(emailTitleXpath));

            List<String> invalidEmails = new ArrayList<>();
            Collections.addAll(invalidEmails, "@mail.com", ",@m.com", "a@m!.com", "a@m.c");

            for (String invalidEmail : invalidEmails) {
                textField.clear();
                textField.sendKeys(invalidEmail);
                test.log(Status.PASS, "Entered " + invalidEmail + " into 'Email' textfield.");
                emailTitle.click(); // Requires clicking anywhere else to affirm textField contents and for possible ErrorMsg to appear

                WebElement ErrorMsg = driver.findElement(By.xpath(emailInputErrorXpath));
                assertEquals(ErrorMsg.getText(), emailInputErrorMessage);
                test.log(Status.PASS, "Correct error message shown about invalid email");
            }
        } catch (AssertionError assertionError) {
            test.log(Status.FAIL, "assert value was incorrect.");
            Assert.fail("assert value was incorrect.");
        } catch (NoSuchElementException noSuchElementException) {
            test.log(Status.FAIL, "Invalid xpath: " + noSuchElementException);
            Assert.fail("Invalid xpath: " + noSuchElementException);
        } finally {
            extentReports.flush();
        }
    }

    @Test
    public void requiredFieldsFormCompletionTest() throws InterruptedException {
        test = extentReports.createTest("requiredFieldsFormCompletionTest", "Complete the test with ONLY required fields");

        try {
            WebElement nameTextField = driver.findElement(By.xpath(nameFieldXpath));
            nameTextField.sendKeys("User Name 123");
            test.log(Status.PASS, "Input information into 'Name' textfield");

            WebElement emailTextField = driver.findElement(By.xpath(emailFieldXpath));
            emailTextField.sendKeys("userName@global.com");
            test.log(Status.PASS, "Input valid information into 'Email' textfield");

            WebElement addressTextField = driver.findElement(By.xpath(addressFieldXpath));
            addressTextField.sendKeys("Fancy Street Boulevard 1.2 @%!");
            test.log(Status.PASS, "Input information into 'Address' textfield");

            driver.findElement(By.xpath(confirmButtonXpath)).click();
            test.log(Status.PASS, "Pressing 'Saada ära' succeeded.");

            WebElement successText = driver.findElement(By.xpath(submitScreenText));
            assertEquals(successText.getText(), successfulApplicationText);
            test.log(Status.PASS, "Successfully completed application message shown.");

            driver.findElement(By.xpath(submitScreenRedoButton)).click();
            test.log(Status.PASS, "Pressing 'Muutke oma vastust' succeeded.");
            Thread.sleep(500);

            WebElement emailTitle = driver.findElement(By.xpath(emailTitleXpath));
            assertEquals(emailTitle.getText(), "Email");
            test.log(Status.PASS, "Successfully returned to original application form.");
        } catch (AssertionError assertionError) {
            test.log(Status.FAIL, "assert value was incorrect.");
            Assert.fail("assert value was incorrect.");
        } catch (NoSuchElementException noSuchElementException) {
            test.log(Status.FAIL, "Invalid xpath: " + noSuchElementException);
            Assert.fail("Invalid xpath: " + noSuchElementException);
        } finally {
            extentReports.flush();
        }
    }

    @Test
    public void allFieldsFormCompletionTest() throws InterruptedException {
        test = extentReports.createTest("allFieldsFormCompletionTest", "Complete the test with ALL the fields");

        try {
            WebElement option3 = driver.findElement(By.xpath(option3Xpath));
            option3.click();
            test.log(Status.PASS, "Option 3 was clicked");

            WebElement nameTextField = driver.findElement(By.xpath(nameFieldXpath));
            nameTextField.sendKeys("User Name 123");
            test.log(Status.PASS, "Input information into 'Name' textfield");
            String inputtedNameFieldText = nameTextField.getAttribute("data-initial-value");

            WebElement emailTextField = driver.findElement(By.xpath(emailFieldXpath));
            emailTextField.sendKeys("userName@global.com");
            test.log(Status.PASS, "Input valid information into 'Email' textfield");

            WebElement addressTextField = driver.findElement(By.xpath(addressFieldXpath));
            addressTextField.sendKeys("Fancy Street Boulevard 1.2 @%!");
            test.log(Status.PASS, "Input information into 'Address' textfield");

            WebElement phoneTextField = driver.findElement(By.xpath(phoneNrFieldXpath));
            phoneTextField.sendKeys("k2asfm!@ka86546smd?.fknasf");
            test.log(Status.PASS, "Input random characters into 'Phone number' field.");
            phoneTextField.clear();
            phoneTextField.sendKeys("56123456");
            test.log(Status.PASS, "Input information into 'Phone number' field.");

            WebElement commentTextField = driver.findElement(By.xpath(commentsFieldXpath));
            commentTextField.sendKeys("This is a test of random stuff: 1231mjnfgdsjni913ur912j8YB!G/Y!TJ=!)UF()");
            test.log(Status.PASS, "Input information into 'Comments' field.");

            driver.findElement(By.xpath(confirmButtonXpath)).click();
            test.log(Status.PASS, "Pressing 'Saada ära' succeeded.");

            WebElement successText = driver.findElement(By.xpath(submitScreenText));
            assertEquals(successText.getText(), successfulApplicationText);
            test.log(Status.PASS, "Successfully completed application message shown.");

            driver.findElement(By.xpath(submitScreenRedoButton)).click();
            test.log(Status.PASS, "Pressing 'Muutke oma vastust' succeeded.");
            Thread.sleep(500);

            WebElement emailTitle = driver.findElement(By.xpath(emailTitleXpath));
            assertEquals(emailTitle.getText(), "Email");
            test.log(Status.PASS, "Successfully returned to original application form.");

            String currentNameTextField = driver.findElement(By.xpath(nameFieldXpath)).getAttribute("data-initial-value");
            assertEquals(currentNameTextField, inputtedNameFieldText);
            test.log(Status.PASS, "Information still retained from last time");
        } catch (AssertionError assertionError) {
            test.log(Status.FAIL, "assert value was incorrect.");
            Assert.fail("assert value was incorrect.");
        } catch (NoSuchElementException noSuchElementException) {
            test.log(Status.FAIL, "Invalid xpath: " + noSuchElementException);
            Assert.fail("Invalid xpath: " + noSuchElementException);
        } finally {
            extentReports.flush();
        }
    }

    public void noTextInput(String xpath, String textFieldName, String errorXpath) {
        try {
            WebElement textField = driver.findElement(By.xpath(xpath));
            textField.sendKeys("");
            test.log(Status.PASS, "Don't enter anything into '" + textFieldName +  "' field.");

            driver.findElement(By.xpath(confirmButtonXpath)).click();
            test.log(Status.PASS, "Pressing 'Saada ära' succeeded.");

            WebElement ErrorMsg = driver.findElement(By.xpath(errorXpath));
            assertEquals(ErrorMsg.getText(), errorMessage);
            test.log(Status.PASS, "Correct error message shown.");
        } catch (AssertionError assertionError) {
            test.log(Status.FAIL, "assert value was incorrect.");
            Assert.fail("assert value was incorrect.");
        } catch (NoSuchElementException noSuchElementException) {
            test.log(Status.FAIL, "Invalid xpath: " + noSuchElementException);
            Assert.fail("Invalid xpath: " + noSuchElementException);
        } finally {
            extentReports.flush();
        }
    }
}