package com.nology.musicplayer.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Perform any setup before calling {@link PreparedStatement} update method.
 */
public interface PreparedStatementSetup {

    /**
     * Perform any actions before calling the executeUpdate() on the {@link PreparedStatement}.
     * @param statement
     * @throws SQLException
     */
    void beforeExecution(PreparedStatement statement) throws SQLException;

}
