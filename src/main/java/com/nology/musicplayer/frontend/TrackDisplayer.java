package com.nology.musicplayer.frontend;

import com.nology.musicplayer.data.Track;

import java.util.List;

/**
 * Renders information about a track to the console or web or whatever.
 */
public interface TrackDisplayer {

    void displayTrack(Track track);
    void displayTracks(List<Track> trackList);

}
