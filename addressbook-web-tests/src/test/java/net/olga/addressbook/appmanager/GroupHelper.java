package net.olga.addressbook.appmanager;

import net.olga.addressbook.models.GroupData;
import net.olga.addressbook.models.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public Groups groupCache = null;

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void returnGroupPage() {
        click(By.linkText("group page"));
    }

    public void deleteSelected() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectById(int id) {
            wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
        returnGroupPage();
    }

    public void delete(int index) {
        select(index);
        deleteSelected();
        groupCache = null;
        returnGroupPage();
    }

    public void delete(GroupData group) {
        selectById(group.getId());
        deleteSelected();
        groupCache = null;
        returnGroupPage();
    }

    public void modify(GroupData groupData) {
        selectById(groupData.getId());
        initGroupModification();
        fillGroupForm(groupData);
        submitGroupModification();
        groupCache = null;
        returnGroupPage();
    }

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        Groups groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element: elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }
    public boolean isThereGroup() {
        return isElementPresnt(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}
