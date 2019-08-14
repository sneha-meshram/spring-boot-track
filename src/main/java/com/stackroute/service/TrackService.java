package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    public Track save(Track track) throws TrackAlreadyExistsException;

    public Track getTrackById(int id) throws TrackNotFound;

    public List<Track> getAllTrack() throws TrackNotFound;

    public Optional<Track> deleteTrackById(int id) throws TrackNotFound;

    public Optional<Track> updateTrackById(int id);

    public List<Track> getTrackByName(String name) throws TrackNotFound;


}
