package com.nology.musicplayer.service;

import com.nology.musicplayer.data.StarRating;
import com.nology.musicplayer.data.Track;

import java.time.LocalDate;
import java.util.List;

public interface TrackService {

    List<Track> getAllTracks();

    Track createTrack(String id, String name, String artist, LocalDate releasedOn, StarRating starRating);

    Track updateTrack(Track track);

}
