package com.nology.musicplayer;

import com.nology.musicplayer.controller.MusicController;
import com.nology.musicplayer.database.DBUtils;
import com.nology.musicplayer.database.DatabaseInitialiser;
import com.nology.musicplayer.database.JdbcTrackService;
import com.nology.musicplayer.frontend.ConsoleTrackDisplayer;
import com.nology.musicplayer.frontend.TrackDisplayer;
import com.nology.musicplayer.player.TextTrackPlayer;
import com.nology.musicplayer.player.TrackPlayer;

public class MusicPlayer {

    enum RendererType {
        console, web;
    }

    enum PlayerType {
        text, audio;
    }

    private void buildAndStart() {
        DBUtils dbUtils = new DBUtils();

        // build DatabaseInitialiser
        DatabaseInitialiser databaseInitialiser = new DatabaseInitialiser(dbUtils);

        // initialise DB
        databaseInitialiser.initialiseDatabase();

        // build JdbcTrackService
        JdbcTrackService trackService = new JdbcTrackService(dbUtils);

        // build a TrackPlayer
        TextTrackPlayer trackPlayer = new TextTrackPlayer();

        // build a TrackDisplayer
        ConsoleTrackDisplayer trackDisplayer = new ConsoleTrackDisplayer();

        // finally. build the MusicController
        MusicController controller = new MusicController();
        controller.setTrackPlayer(trackPlayer);
        controller.setTrackDisplayer(trackDisplayer);
        controller.setTrackService(trackService);

        // initialise the controller
        controller.initialiseController();
        controller.run();
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        player.buildAndStart();
    }

}
