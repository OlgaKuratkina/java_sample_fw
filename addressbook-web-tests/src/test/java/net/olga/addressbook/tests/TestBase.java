package net.olga.addressbook.tests;

import net.olga.addressbook.appmanager.ApplicationManager;
import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import net.olga.addressbook.models.GroupData;
import net.olga.addressbook.models.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected final static ApplicationManager app = new ApplicationManager(
            System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    public void verifyGroupListinUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups db = app.db().groups();
            Groups ui = app.group().all();
            assertThat(ui, equalTo(db.stream().map((g) -> new GroupData()
                    .withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
    public void verifyContactListinUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts db = app.db().contacts();
            Contacts ui = app.contact().all();
            assertThat(ui, equalTo(db.stream().map((c) -> new ContactData()
                    .withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName())
                    .withEmail(c.getEmail()))
                    .collect(Collectors.toSet())));
        }
    }

}
