package pl.edu.pjatk.tau;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

/**
 * Created by Boberkowy on 29.03.2017.
 */

@FixMethodOrder(MethodSorters.JVM)
public class BlogTest {

    private static WebDriver driver;


    @BeforeClass
    public static void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "\\Users\\Boberkowy\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void loginFormTest(){
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();

        driver.get("localhost:8080/tin_blog/");
        driver.findElement(By.id("loginButton")).click();
        WebElement loginTextBox = driver.findElement(By.id("loginTextBox"));
        WebElement passTextBox = driver.findElement(By.name("password"));
        assertNotNull(loginTextBox);
        assertNotNull(passTextBox);
        takeScreenshot(functionName);
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

    @Test
    public void enterGoodLoginAndBadPasswordTest(){
        String functionName = new Object(){}.getClass().getEnclosingMethod().getName();


        driver.get("localhost:8080/tin_blog/");
        driver.findElement(By.id("loginButton")).click();
        driver.findElement(By.id("loginTextBox")).sendKeys("user");
        driver.findElement(By.id("passTextBox")).sendKeys("badpass");
        driver.findElement(By.name("loggin")).click();

        String text = " Wprowadzono nieprawidlowe login lub haslo";
        WebElement msg = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        assertNotNull(msg);
        takeScreenshot(functionName);

    }

    @Test
    public void enterGoodLoginAndGoodPasswordTest(){
        String name = new Object(){}.getClass().getEnclosingMethod().getName();

        driver.get("localhost:8080/tin_blog/");
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
    public void shouldFindSessionID(){
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        driver.get("localhost:8080/tin_blog/");
        driver.findElement(By.id("loginButton")).click();
        driver.findElement(By.id("loginTextBox")).sendKeys("useru");
        driver.findElement(By.id("passTextBox")).sendKeys("useru");
        driver.findElement(By.name("loggin")).click();

        WebElement msg = driver.findElement(By.id("session"));
        assertNotNull(msg);

        takeScreenshot(name);
        driver.findElement(By.linkText("WYLOGUJ")).click();

    }


    @Test
    public void shouldDetectWorkingJS(){

        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        driver.get("localhost:8080/tin_blog/");
        driver.findElement(By.id("buttonJS")).click();
        String text = "Jakby co to JS dziala";
        WebElement msg = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));
        assertNotNull(msg);
        takeScreenshot(name);


    }


    private void takeScreenshot(String functionName){
        File file = new File(".");
        String destFolder = file.getAbsolutePath();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(destFolder + "\\target\\" + functionName +" photo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @AfterClass
    public static void clean() {
        driver.quit();
    }
}
