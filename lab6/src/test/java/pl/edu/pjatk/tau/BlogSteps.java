package pl.edu.pjatk.tau;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;
/**
 * Created by Boberkowy on 08.04.2017.
 */
public class BlogSteps{

    private final Page page;

    public BlogSteps(Page page){
        this.page = page;
    }


    @Given("user is on tinpage")
    public void userIsOnTinPage(){
        page.tinBlog().open();
        page.tinBlog().click("loginButton");
    }

    @Given("user is logged in")
    public void userisLoggedIn(){
        page.tinBlog().open();
        page.tinBlog().click("loginButton");
        page.tinBlog().insertData("user","user");

    }

    @When("user enter $loginLinkText and $passLinkText as login and password")
    public void userEntersProperCreds(String loginLinkText, String passLinkText){
        page.tinBlog().insertData(loginLinkText,passLinkText);
    }

    @When("user enter inproper creds as $loginLinkText and $passLinkText as login and password")
    public void userEntersInProperCreds(String loginLinkText, String passLinkText){
        page.tinBlog().insertData(loginLinkText,passLinkText);
    }

    @When("user visit new page")
    public void userVisitNewPage(){
        page.tinBlog().get("www.google.pl");
    }


    @Then("user should be logged in and $linkText visible")
    public void userLoggedIn(String linkText){
//        assertTrue(page.tinBlog().findElement(By.linkText(linkText)).toString().contains(linkText));
        WebElement webElement = page.tinBlog().findElement(By.linkText(linkText));
        String x = webElement.toString();
        assertTrue(x.contains(linkText));
        webElement.click();
    }

    @Then("user shouldnt be logged in and $linkText visible")
    public void loginError(String linkText){
        WebElement webElement = page.tinBlog().findElement(By.id("loginButton"));
        String x = webElement.getText();
        assertTrue(x.contains(linkText));

    }
    @Then("user come back to blog")
    public void userComeBackToBlog(){
        page.tinBlog().open();
    }

    @Then("user is still logged in")
    public void userIsStillLoggedIn(){
        WebElement webElement = page.tinBlog().findElement(By.linkText("WYLOGUJ"));
        String x = webElement.toString();
        assertTrue(x.contains("WYLOGUJ"));
    }
}
