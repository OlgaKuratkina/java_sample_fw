package mantis.appmanager;

import mantis.models.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DbHelper {

    private final ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<User> getUsers() {
        Connection conn = null;
        Set<User> users = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
                    "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet result_query = st.executeQuery("select username, email from mantis_user_table");
            users = new HashSet<User>();
            while (result_query.next()) {
                users.add(new User().withName(result_query.getString("username"))
                        .withEmail(result_query.getString("email")));
            }
            result_query.close();
            st.close();
            conn.close();
            System.out.println(users);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return users;
    }
}
