package net.olga.addressbook.tests;

import net.olga.addressbook.models.GroupData;
import net.olga.addressbook.models.Groups;
import org.testng.annotations.Test;

import java.sql.*;

public class DBConnTest {

    @Test
    public void testDBconn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" +
                            "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet result_query = st.executeQuery("select group_id, group_name from group_list");
            Groups groups = new Groups();
            while (result_query.next()) {
                groups.add(new GroupData().withId(result_query.getInt("group_id"))
                        .withName(result_query.getString("group_name")));
            }
            result_query.close();
            st.close();
            conn.close();
            System.out.println(groups);

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
