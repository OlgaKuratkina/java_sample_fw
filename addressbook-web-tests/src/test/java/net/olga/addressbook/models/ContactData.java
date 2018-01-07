package net.olga.addressbook.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "middlename")
    private String middleName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "nickname")
    private String nick;
    @Expose
    @Column
    private String title;
    @Column
    @Type(type = "text")
    private String address;
    @Expose
    @Column
    @Type(type = "text")
    private String email;
    @Transient
    private String email2;
    @Transient
    private String email3;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (nick != null ? !nick.equals(that.nick) : that.nick != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        return workPhone != null ? workPhone.equals(that.workPhone) : that.workPhone == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        return result;
    }

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")

    private String mobilePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String group;

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;

    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNick(String nick) {
        this.nick = nick;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getEmail2() {
        return email2;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail3() {

        return email3;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllEmails() {
        return allEmails;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nick='" + nick + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +

                ", email='" + email + '\'' +
                ", homePhone='" + homePhone + '\'' +
                '}';
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
