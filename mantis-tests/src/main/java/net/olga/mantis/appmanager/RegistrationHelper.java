package net.olga.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends BaseHelper {

    public RegistrationHelper(ApplicationManager app) {
        super(app);

    }
    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        wd.manage().window().maximize();
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String link, String password, String realname) {
        wd.get(link);
        type(By.name("realname"), realname);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type=\"submit\"]"));
    }

    public void applyChangePass(String user) {
        wd.get(app.getProperty("web.baseUrl")+"/manage_user_page.php");

    }
}
