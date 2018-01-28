package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class BaseHelper {
    protected final WebDriver wd;
    protected ApplicationManager app;

    public BaseHelper(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existText = wd.findElement(locator).getAttribute("value");
            if (! existText.equals(text)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void acceptAlert() {
        try {
            wd.switchTo().alert().accept();

        } catch (NoAlertPresentException e) {
            System.out.println("Alert was not found, do nothing");
        }
    }

    public boolean isElementPresnt(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String initUserPassChange(String username) {
        wd.get(app.getProperty("web.baseUrl")+"/manage_user_page.php");
//        wd.get("/manage_user_page.php");
        wd.findElement(By.linkText(username)).click();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        System.out.println(email);
        click(By.xpath("//input[@value=\"Reset Password\"]"));
        return email;
    }
    public Boolean finishPasswordChange(String link, String password) {
        wd.get(link);
//        type(By.name("realname"), realname);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type=\"submit\"]"));
        return Boolean.TRUE;
    }
    public void login(String user, String password) {
//        wd.get(app.getProperty("web.baseUrl") + "/login.php");
        type(By.name("username"), user);
        type(By.name("password"), password);
        click(By.xpath("//input[@type=\"submit\"]"));
    }
}
