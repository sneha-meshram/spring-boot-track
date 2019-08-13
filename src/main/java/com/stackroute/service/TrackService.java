package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    public Track save(Track track) throws TrackAlreadyExistsException;
    public Track getTrackById(int id) throws TrackNotFound;
    public List<Track> getAllTrack()throws TrackNotFound;
    public Track deleteTrackById(int id) throws Exception;
    public Track updateTrackById(int id) throws Exception;
    public Track getTrackByName(String name)throws TrackNotFound;



}
