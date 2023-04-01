package respecker.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {
    @Test
    void testInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "admin'; #";
        String password = "salah";
        String sql = "SELECT * FROM admin WHERE username = '" + username +
                "' AND password = '" + password + "'";

        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()){
            // sukses login
            System.out.println("sukses login");
        } else {
            // gagal login
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
