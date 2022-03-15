package com.nology.musicplayer.database;

import com.nology.musicplayer.data.StarRating;
import com.nology.musicplayer.exceptions.TrackDatabaseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

import static com.nology.musicplayer.database.DatabseConstants.JDBC_DATABASE_URL;

@Component
public class DBUtils implements DatabseConstants {

    /**
     * JDBC driver class name.
     */
    @Value("${jdbc.classDriver}")
    private String jdbcDriver;

    /**
     * JDBC connection URL
     */
    @Value("${jdbc.url}")
    private String jdbcDatabaseUrl;

    /**
     * Username for database
     */
    @Value("${jdbc.username}")
    private String dbUsername;

    /**
     * Password for database.
     */
    @Value("${jdbc.password}")
    private String dbPassword;

    public void performDBAction(String sql, boolean throwException) {

        try (Connection connection = DriverManager.getConnection( this.jdbcDatabaseUrl )) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                boolean result = statement.execute();

            } catch (Exception e) {
                e.printStackTrace();
                if (throwException) {
                    throw new TrackDatabaseException("Exception attempting to perform action: " + sql, e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TrackDatabaseException("Exception attempting to perform action: " + sql, e);
        }

    }

    public void performDBUpdate(String sql, PreparedStatementSetup statementSetup) {

        try (Connection connection = DriverManager.getConnection(JDBC_DATABASE_URL)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statementSetup.beforeExecution(statement);

                statement.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
                throw new TrackDatabaseException("Exception attempting to perform action: " + sql, e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TrackDatabaseException("Exception attempting to perform action: " + sql, e);
        }

    }

    public <T> T performDBQuery(String sql, ResultSetHandler handler) {

        try (Connection connection = DriverManager.getConnection(JDBC_DATABASE_URL)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                ResultSet resultSet = statement.executeQuery();

                return handler.handleResultSet(resultSet);

            } catch (Exception e) {
                e.printStackTrace();
                throw new TrackDatabaseException("Exception attempting to perform query: " + sql, e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TrackDatabaseException("Exception attempting to perform action: " + sql, e);
        }
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public void setJdbcDatabaseUrl(String jdbcDatabaseUrl) {
        this.jdbcDatabaseUrl = jdbcDatabaseUrl;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
