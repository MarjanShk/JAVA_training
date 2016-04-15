package addressbook.tests;

import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

/**
 * Created by Admin on 14.04.2016.
 */
public class DBConnectionTest {
    @Test
    public static void testDBConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                    "user=root&password=");
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("select group_id, group_name, group_header, group_footer " +
                    "from `group_list`");
            Groups groups = new Groups();
            while (res.next()) {
                groups.add(new GroupData().withId(res.getInt("group_id")).withName(res.getString("group_name"))
                        .withHeader(res.getString("group_header")).withFooter(res.getString("group_footer")));
            }
            System.out.println(groups);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
