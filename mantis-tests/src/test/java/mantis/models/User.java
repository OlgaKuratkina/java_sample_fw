package mantis.models;

public class User {

    private String name;

    public String getName() {
        return name;
    }

    public User withName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;

    }

    private String email;
}
