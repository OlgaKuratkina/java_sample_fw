package net.olga.addressbook.tests;

import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailsTest extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Miguel").withMiddleName("Alberto")
                    .withLastName("Navarro").withNick("Mig").withTitle("Mr")
                    .withEmail("mig@navarro.io").withEmail3("sfjkg@kfh.oi"));
        }
    }

    @Test
    public void testContactEmails() {
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        ContactData contact = before.iterator().next();
        ContactData contactFromEditForm = app.contact().infoFromEditForm(contact);
        System.out.println(contactFromEditForm);


        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactFromEditForm)));

    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailsTest::cleaned)
                .collect(Collectors.joining("\n"));

    }

    public static String cleaned(String email) {
        return email.replaceAll("\\s", "").replaceAll("[-()']", "");
    }
}
