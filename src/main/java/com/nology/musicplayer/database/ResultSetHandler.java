package com.nology.musicplayer.database;

import java.sql.ResultSet;

/**
 * An interface for handling a {@link ResultSet} within a {@link java.sql.PreparedStatement} call.
 */
public interface ResultSetHandler {

    <T> T handleResultSet(ResultSet resultSet);

}
