package respecker.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class PreparedStatementTest {

    @Test
    void testInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String username = "admin'; #";
        String password = "salah";

        String sql = "SELECT * FROM admin where username = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            // sukses login
            System.out.println("sukses login");
        } else {
            // gagal login
            System.out.println("gagal login");
        }

        preparedStatement.close();
        connection.close();
    }
}
