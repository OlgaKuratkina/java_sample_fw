package net.olga.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import net.olga.addressbook.models.GroupData;
import net.olga.addressbook.models.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {
    @BeforeMethod
    public void ensureContactAndGroup() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                    .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                    .withEmail("mig@navarro.io"));
        }
    }
    @Test
    public void testContactAddGroup() {
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();

        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();

        app.contact().addToGroup(contact, group);
        app.goTo().HomePage();

        Contacts after = app.db().contacts();
        assertThat(before.size(), equalTo(after.size()));

        assertThat(before.withOut(contact).withAdded(contact.inGroup(group)), equalTo(after));

    }
}
