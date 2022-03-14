package com.nology.musicplayer.database;

import com.nology.musicplayer.data.StarRating;
import com.nology.musicplayer.data.Track;
import com.nology.musicplayer.exceptions.TrackDatabaseException;
import com.nology.musicplayer.service.TrackService;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcTrackService implements TrackService {

    private DBUtils dbUtils;

    public JdbcTrackService(DBUtils dbUtils) {
        this.dbUtils = dbUtils;
    }

    @Override
    public List<Track> getAllTracks() {

        ResultSetHandler handler = new ResultSetHandler() {
            @Override
            public List<Track> handleResultSet(ResultSet resultSet) {

                List<Track> tracks = new ArrayList<>();
                while (true) {

                    try {

                        if (!resultSet.next()) break;

                        Track track = new Track();
                        String id = resultSet.getString(1);
                        String name = resultSet.getString(2);
                        String artist = resultSet.getString(3);
                        Date releaseDate = resultSet.getDate(4);
                        StarRating rating = null;
                        if (resultSet.getString(5) != null) {
                            rating = StarRating.valueOf(resultSet.getString(5));
                        }

                        System.out.println(String.format("id: %s, name: %s, artist: %s, rating: %s, releaseOn: %s",
                                id, name, artist, rating != null ? rating.getText() : "not rated", releaseDate));

                        track.setId(id);
                        track.setName(name);
                        track.setArtist(artist);
                        if (releaseDate != null) {
                            track.setReleaseDate(releaseDate.toLocalDate());
                        }
                        track.setStarRating(rating);

                        tracks.add(track);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        throw new TrackDatabaseException("Attempting to find all tracks", e);
                    }
                }
                return tracks;
            }
        };

        List<Track> tracks = dbUtils.performDBQuery("select * from tracks;", handler);

        return new ArrayList<>( tracks );
    }

    @Override
    public Track createTrack(String id, String name, String artist, LocalDate releasedOn, StarRating starRating) {
        return null;
    }

    @Override
    public Track updateTrack(Track track) {
        return null;
    }


}
