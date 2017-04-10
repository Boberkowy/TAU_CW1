package pl.edu.pjatk.tau;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Boberkowy on 08.04.2017.
 */
public class BlogTest {
    private static WebDriver driver;
    WebElement element;

    @BeforeClass
    public static void driverSetup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability("takesScreenshot", true);
        caps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "F:\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe"
        );
        driver = new PhantomJSDriver(caps);
    }

    @AfterClass
    public static void cleanp() {
        driver.quit();
    }


    @Test
    public void enterGoodLoginAndPassShouldBeLoggedInTest(){
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();

        driver.get("http://localhost:8080/TIN_BLOG/");
        driver.findElement(By.id("loginButton")).click();
        driver.findElement(By.id("loginTextBox")).sendKeys("useru");
        driver.findElement(By.id("passTextBox")).sendKeys("useru");
        driver.findElement(By.name("loggin")).click();

        WebElement msg = driver.findElement(By.linkText("WYLOGUJ"));
        assertNotNull(msg);

        takeScreenshot(name);
        driver.findElement(By.linkText("WYLOGUJ")).click();
    }


    @Test
    public void enterBadLoginAndPasswordTest(){
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();


        driver.get("localhost:8080/tin_blog/");
        driver.findElement(By.id("loginButton")).click();
        driver.findElement(By.id("loginTextBox")).sendKeys("badlogin");
        driver.findElement(By.id("passTextBox")).sendKeys("badpass");
        driver.findElement(By.name("loggin")).click();

        String text = " Wprowadzono nieprawidlowe login lub haslo";
        WebElement msg = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        assertNotNull(msg);

        takeScreenshot(functionName);

    }


    private void takeScreenshot(String functionName) {
        File file = new File(".");
        String destFolder = file.getAbsolutePath();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(destFolder + "\\target\\" + functionName + " photo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
