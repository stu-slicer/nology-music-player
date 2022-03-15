package com.nology.musicplayer.database;

import com.nology.musicplayer.data.StarRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.time.LocalDate.parse;

@Component
public class DatabaseInitialiser {

    public static final String CREATE_TRACKS = "create table tracks (" +
            " track_id varchar(20) not null," +
            " name varchar(200) not null," +
            " artist varchar(200) not null," +
            " release_date date null," +
            " rating varchar(10) null" +
            ");";

    public static final String DROP_TRACKS = "DROP TABLE tracks;";

    public static final String SQL_INSERT_TRACK = "INSERT INTO tracks VALUES( ?, ?, ?, ?, ?);";

    private DBUtils dbUtils;

    @Autowired
    public DatabaseInitialiser(DBUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    /**
     * Initialise the database; by recreating the database and adding in the tracks.
     */
    @PostConstruct
    public void initialiseDatabase() {

        try {
//            performDBAction(CREATE_TRACKS, false);
            replaceDb();

            createTrack("1", "The Whole of the Moon", "Waterboys", StarRating._5Stars, parse( "1978-11-19" ) );
            createTrack("2", "Wuthering Heights", "Kate Bush", StarRating._5Stars, parse( "1986-06-17" ) );
            createTrack("3", "Let's Dance", "David Bowie", StarRating._5Stars, parse( "1982-04-12" ) );
            createTrack("4", "Help!", "The Beatles", StarRating._5Stars, parse( "1964-03-03" ) );
            createTrack("5", "Easy On Me", "Adele", StarRating._4Stars, parse( "1921-11-05" ) );
            createTrack("6", "Smile", "Lily Allen", StarRating._4Stars, parse( "2003-06-09" ) );
            createTrack("7", "Rehab", "Anmy Winehouse", StarRating._5Stars, parse( "2006-09-09" ) );
            createTrack("8", "Strange", "Celeste", StarRating._4Stars, parse( "2020-10-30" ) );
            createTrack("9", "Save Me", "Queen", StarRating._4Stars, parse( "1981-04-21" ) );

      } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createTrack(String id, String name, String artist, StarRating rating, LocalDate releasedOn) throws SQLException {
        PreparedStatementSetup setup = new PreparedStatementSetup() {
            @Override
            public void beforeExecution(PreparedStatement statement) throws SQLException {
                statement.setString(1, id );
                statement.setString(2, name );
                statement.setString(3, artist );
                statement.setDate(4, Date.valueOf(releasedOn) );
                statement.setString(5, rating.toString() );
            }
        };

        dbUtils.performDBUpdate(SQL_INSERT_TRACK, setup);
    }

    public void replaceDb() {
        dbUtils.performDBAction(DROP_TRACKS, false);
        dbUtils.performDBAction(CREATE_TRACKS, false);
    }

}
