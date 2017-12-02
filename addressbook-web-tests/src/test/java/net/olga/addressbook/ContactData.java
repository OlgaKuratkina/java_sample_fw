package net.olga.addressbook;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nick;
    private final String title;
    private final String address;
    private final String homePhone;
    private final String email;

    public ContactData(String firstName, String middleName, String lastName, String nick, String title, String address, String homePhone, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nick = nick;
        this.title = title;
        this.address = address;
        this.homePhone = homePhone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNick() {
        return nick;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getEmail() {
        return email;
    }
}
