import Supports.Browser;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.*;

import static Supports.Browser.*;

public class loginPage {

    @BeforeTest
    void open(){
        Browser.openBrowser("chrome");
        visit("https://the-internet.herokuapp.com/login");
    }

    @Test
    void loginSuccess(){
        find(How.CSS, "#username").sendKeys("tomsmith");
        find(How.CSS, "#password").sendKeys("SuperSecretPassword!");
        click(How.CSS, "#login > button");
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure" );
    }

    @AfterTest
    void quit(){
        close();
    }
}
