package com.nology.musicplayer.controller;

import com.nology.musicplayer.data.Track;
import com.nology.musicplayer.player.TrackPlayer;
import com.nology.musicplayer.frontend.TrackDisplayer;
import com.nology.musicplayer.service.TrackService;

import java.util.*;

public class MusicController {

    private TrackService trackService;

    private TrackDisplayer trackDisplayer;

    private TrackPlayer trackPlayer;

    private List<Track> playlist = new ArrayList<>();

    private Track currentTrack;

    public void initialiseController() {
        initialise();
    }

    public void run() {

        // welcome screen

        // display first play list


        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Please enter a command: ");
            String command = scanner.nextLine();

            if( command == null || "".equalsIgnoreCase(command) ) {
                System.out.println("Please enter a valid command!");
                help();

            } else {
                command = command.toLowerCase().trim();

            }

            // user wants to quit, return.
            if (command.equals("exit") || command.equals("quit")) {
                System.out.println("I'm out of here!");
                return;

            } else if (command.equals("shuffle")) {
                shuffle();

            } else if( command.equals("list") ) {
                displayList();

            } else if( command.equals("play") ) {
                playTrack( this.getCurrentTrack() );

            } else if( command.startsWith("play ") ) {
                int trackId = extractTrackId(command);
                if (trackId < 1 || trackId > playlist.size()) {
                    System.out.println("Enter a track between 1 and " + playlist.size());
                    continue;
                }
                this.currentTrack = this.playlist.get(trackId - 1);
                playTrack( this.getCurrentTrack() );

            } else if( command.equals("next") ) {
                nextTrack();

            } else if( command.equals("prev") ) {
                previousTrack();

            } else if( command.equals("stop") ) {
                stopTrack();

            } else {
                System.out.println("Please enter a valid command!");
                help();
            }

            // display current

            // select track and play
        }

    }

    private void help() {
        System.out.println("Commands available are:");
        System.out.println("  list\t\tdisplay play list");
        System.out.println("  play\t\tplay the current track");
        System.out.println("  stop\t\tstop playing the current track");
        System.out.println("  next\t\tplay the next track");
        System.out.println("  prev\t\tplay the previous track");
        System.out.println("  shuffle\tshuffle the tracks");
        System.out.println("  exit\t\texit the mighty player");
        System.out.println("  quit\t\texit the marvellous player");
        System.out.println("\n");
    }

    /**
     * Given a command with a track id at the end extract the id.
     * @param command
     * @return the track id or -1 if invalid or missing.
     */
    private int extractTrackId(String command) {
        String[] tokens = command.split(" +");
        try {
            return Integer.valueOf(tokens[1]);
        } catch (NumberFormatException e) {
        }
        return -1;
    }

    private void playTrack(Track track) {
        System.out.println("Playing...");
        this.trackDisplayer.displayTrack(track);
        this.trackPlayer.playTrack(track);
        this.currentTrack = track;
    }

    private void stopTrack() {
        this.trackPlayer.stop();
    }

    private void nextTrack() {
        Track current = getCurrentTrack();
        int index = this.playlist.indexOf(current);
        index = (index + 1) % playlist.size();
        this.currentTrack = this.playlist.get( index );
        this.playTrack(this.currentTrack);
    }

    private void previousTrack() {
        Track current = getCurrentTrack();
        int index = this.playlist.indexOf(current);
        index--;
        if (index < 0) {
            index = this.playlist.size() - 1;
        }
        this.currentTrack = this.playlist.get( index );
        this.playTrack(this.currentTrack);
    }

    private Track getCurrentTrack() {
        if (this.currentTrack == null) {
            return this.playlist.get(0);
        }
        return currentTrack;
    }

    /**
     * Shuffle those tracks. Note the current track is cleared
     */
    protected void shuffle() {
        Collections.shuffle(this.playlist);
        this.currentTrack = null;
        System.out.println("Shuffled play list:");
        trackDisplayer.displayTracks( this.playlist);
    }

    /**
     * Display the playlist.
     */
    protected void displayList() {
        System.out.println("Displaying play list:");
        trackDisplayer.displayTracks( this.playlist );
    }

    /**
     * Initialise the controller. Load all tracks into playlist.
     */
    protected void initialise() {
        this.playlist = trackService.getAllTracks();
    }

    public void setTrackPlayer(TrackPlayer trackPlayer) {
        this.trackPlayer = trackPlayer;
    }

    public void setTrackDisplayer(TrackDisplayer trackDisplayer) {
        this.trackDisplayer = trackDisplayer;
    }

    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }
}
