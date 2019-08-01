package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;

public interface TrackService {

    public Track save(Track track);
    public Track getTrackById(int id);
    public List<Track> getAllTrack(Track track);
    public Track deleteTrackById(int id);
    public Track updateTrackById(int id);



}
