package respecker.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    void testHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/belajar-java-database?serverTimezone=Asia/Jakarta");
        config.setUsername("root");
        config.setPassword("");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        HikariDataSource dataSource = new HikariDataSource(config);

        try {
            Connection connection = dataSource.getConnection();
            System.out.println("database connected");

            // mengembalikan ke hikari datasource
            connection.close();

            dataSource.close();
        } catch (SQLException exception){
            Assertions.fail(exception);
        }
    }

    @Test
    void testUtil() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
    }
}