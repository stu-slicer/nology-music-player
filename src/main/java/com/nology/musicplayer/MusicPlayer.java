package com.nology.musicplayer;

import com.nology.musicplayer.controller.MusicController;
import com.nology.musicplayer.database.DBUtils;
import com.nology.musicplayer.database.DatabaseInitialiser;
import com.nology.musicplayer.database.JdbcTrackService;
import com.nology.musicplayer.frontend.ConsoleTrackDisplayer;
import com.nology.musicplayer.frontend.TrackDisplayer;
import com.nology.musicplayer.player.TextTrackPlayer;
import com.nology.musicplayer.player.TrackPlayer;

import javax.naming.OperationNotSupportedException;

public class MusicPlayer {

    enum RendererType {
        console, web;
    }

    enum PlayerType {
        text, audio;
    }

    private void buildAndStart() {
        DBUtils dbUtils = buildDBUtils();

        // initialise the controller
        //controller.initialiseController();

    }

    // BUILD MusicController
    private MusicController buildMusicController(DBUtils dbUtils) {
        throw new UnsupportedOperationException();
    }

    // BUILD TrackDisplayer
    private TrackDisplayer buildTrackDisplayer(RendererType type) {
        throw new UnsupportedOperationException();
    }

    // BUILD TrackPlayer
    private TrackPlayer buildTrackPlayer(PlayerType type) {
        throw new UnsupportedOperationException();
    }

    // BUILD JdbcTrackService
    private JdbcTrackService buildJdbcTrackService(DBUtils dbUtils) {
        throw new UnsupportedOperationException();
    }

    /**
     * Builds and initialises the {@link DatabaseInitialiser} object.
     * @param dbUtils
     * @return
     */
    private DatabaseInitialiser buildDatabaseInitialiser(DBUtils dbUtils) {
        throw new UnsupportedOperationException();
    }

    /**
     * Builds and initalises the {@link DBUtils} class.
     * @return
     */
    private DBUtils buildDBUtils() {
        return new DBUtils();
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        player.buildAndStart();
    }

}
