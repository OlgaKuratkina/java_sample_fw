package net.olga.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NaviHelper extends BaseHelper {

    public NaviHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }
}
