package net.olga.addressbook.appmanager;

import net.olga.addressbook.models.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNick());
        type(By.name("title"), contactData.getTitle());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelected() {
        click(By.xpath("//div[1]/div[4]/form[2]/div[2]/input"));
    }

    public void initContactModifyByIndex(int index) {
        String locator = "//tr//td[8]/a";
        List<WebElement> cells = wd.findElements(By.xpath(locator));
        cells.get(index).click();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
    }

    public void delete(int index) {
        select(index);
        deleteSelected();
        acceptAlert();
    }

    public void modify(int index, ContactData edited_contact) {
        initContactModifyByIndex(index);
        fillContactForm(edited_contact);
        submitContactModification();
        gotoHomePage();
    }

    public void gotoHomePage() {
        if (isElementPresnt(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public List<ContactData> list() {
        List <ContactData> contacts = new ArrayList<>();
        List <WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement el: elements) {
            List <WebElement> cels = el.findElements(By.tagName("td"));
            int id = Integer.parseInt(cels.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String last_name = cels.get(1).getText();
            String first_name = cels.get(2).getText();
            String address = cels.get(3).getText();
            String email = cels.get(4).getText();
//            String phones = cels.get(5).getText();
            contacts.add(new ContactData()
                    .withId(id).withFirstName(first_name).withLastName(last_name)
                    .withAddress(address).withEmail(email));
        }
        return contacts;
    }
}
