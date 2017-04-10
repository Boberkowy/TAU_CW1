package pl.edu.pjatk.tau;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebElement;
import pl.edu.pjatk.tau.page.TinBlog;

/**
 * Created by Boberkowy on 08.04.2017.
 */
public class Page {

    private WebDriverProvider driverProvider;

    private TinBlog tinBlog;

    public Page(WebDriverProvider driverProvider){
        super();
        this.driverProvider = driverProvider;
    }

    public TinBlog tinBlog(){
     if(tinBlog == null)
        tinBlog = new TinBlog(driverProvider);
     return tinBlog;
    }

}
