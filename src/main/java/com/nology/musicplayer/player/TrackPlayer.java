package com.nology.musicplayer.player;

import com.nology.musicplayer.data.Track;

/**
 * A simple player for a track.
 */
public interface TrackPlayer {

    /**
     * Play the give {@link Track}
     * @param track
     */
    void playTrack(Track track);

    /**
     * Stop any playing track.
     */
    void stop();

    /**
     * Pause any playing track.
     */
    void pause();

    /**
     * Resume any paused track.
     */
    void resume();

}
