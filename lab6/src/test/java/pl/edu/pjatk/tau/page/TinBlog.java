package pl.edu.pjatk.tau.page;

import com.sun.jna.platform.win32.WinDef;
import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Boberkowy on 08.04.2017.
 */
public class TinBlog extends WebDriverPage{

    public TinBlog(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open(){
        get("http://localhost:8080/TIN_BLOG/index.php");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void click(String linkText){
//        findElement(By.id(linkText)).click();
        WebElement e = findElement(By.id(linkText));
        e.click();
    }


    public void insertData(String loginLinkText, String passLinkText){
        findElement(By.id("loginTextBox")).sendKeys(loginLinkText);
        findElement(By.id("passTextBox")).sendKeys(passLinkText);
        findElement(By.name("loggin")).click();
    }

    public boolean findElementByText(String linkText){
        WebElement e = findElement(By.xpath(linkText));
        if(e.getText() != null){
            return true;
        }
        return false;

    }

    public boolean findElementById(String linkText){
        WebElement e = findElement(By.id(linkText));
        if(e != null){
            return true;
        }
        return false;

    }

    public String getClassesForLink(String linkText) {
        WebElement e = findElement(By.className(linkText));
        return e.getAttribute("class");
    }
}
