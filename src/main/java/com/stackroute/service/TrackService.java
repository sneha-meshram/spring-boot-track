package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    public Track save(Track track);
    public Track getTrackById(int id);
    public List<Track> getAllTrack(Track track);
    public Optional <Track> deleteTrackById(int id);
    public Optional<Track> updateTrackById(int id);



}
