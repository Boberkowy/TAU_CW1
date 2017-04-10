package pl.edu.pjatk.tau;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

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

    @When("user enter $loginLinkText and $passLinkText as login and password")
    public void userEntersProperCreds(String loginLinkText, String passLinkText){
        page.tinBlog().insertData(loginLinkText,passLinkText);
        page.tinBlog().findElement(By.name("Zaloguj")).click();
    }

    @Then("user should be logged in and $linkText visible")
    public void userLoggedIn(String linkText){
        assertTrue(page.tinBlog().findElement(By.linkText(linkText)).toString().contains(linkText));
    }

    @Then("user should see error which has id $linkText")
    public void loginError(String linkText){
        assertTrue(page.tinBlog().findElementByText("Wprowadzono nieprawidlowe login lub haslo"));
    }
}
