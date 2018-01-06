package net.olga.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.olga.addressbook.models.ContactData;
import net.olga.addressbook.models.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Target data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcommander = new JCommander(generator);
        try {jcommander.parse(args);}
        catch (ParameterException ex) {
            jcommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        }else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
            } else {
            System.out.println("Unrecognised format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        Writer writer = new FileWriter(file);
        String json = gson.toJson(contacts);
        writer.write(json);
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for(int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("Name %s", i))
                    .withLastName(String.format("Surname %s", i))
                    .withEmail(String.format("email@lala%s", i))
                    .withMobilePhone(String.format("2409056%s", i))
                    .withTitle(String.format("title %s", i)));
        }
        return contacts;
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact: contacts) {
            writer.write(String.format("%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(),
                    contact.getEmail(), contact.getMobilePhone()));
        }
        writer.close();
    }
}
