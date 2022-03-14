package com.nology.musicplayer;

import com.nology.musicplayer.controller.MusicController;
import com.nology.musicplayer.database.DBUtils;
import com.nology.musicplayer.database.DatabaseInitialiser;
import com.nology.musicplayer.database.JdbcTrackService;
import com.nology.musicplayer.frontend.TrackDisplayer;
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

        // initialise DB

        // build JdbcTrackService

        // build a TrackPlayer

        // build a TrackDisplayer

        // finally. build the MusicController


        // initialise the controller
        //controller.initialiseController();

    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        player.buildAndStart();
    }

}
