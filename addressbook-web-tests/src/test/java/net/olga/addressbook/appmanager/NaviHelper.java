package net.olga.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NaviHelper extends BaseHelper {

    public NaviHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresnt(By.tagName("h1")) && isElementPresnt(By.name("new"))
                && wd.findElement(By.tagName("h1")).getText().equals("groups")) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void HomePage() {
        if (isElementPresnt(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }
}
