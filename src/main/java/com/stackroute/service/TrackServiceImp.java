package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImp implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImp(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //return all save track.
    @Override
    public Track save(Track track) {
        Track savedTrack = trackRepository.save(track);

        return savedTrack;
    }

    //return track by id.
    @Override
    public Track getTrackById(int id) {
        Track getTrackById = trackRepository.findById(id).get();
        return getTrackById;
    }

    //return all tracks.
    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    //delete track by giving the id.
    @Override
    public Optional<Track> deleteTrackById(int id) {
        Optional<Track> optionalTrack = deleteTrackById(id);
        if (optionalTrack.isPresent()) {
            Track track = deleteTrackById(id).get();
        }
        return optionalTrack;
    }

    //update track by giving the id.
    @Override
    public Optional<Track> updateTrackById(int id) {
        Optional<Track> optionalUpdate = updateTrackById(id);
        if (optionalUpdate.isPresent()) {
            Track track1 = updateTrackById(id).get();
        }
        return optionalUpdate;
    }

    //returning the track by giving the name.
    @Override
    public List<Track> gettrackByName(String name) {
        List<Track> trackByName = trackRepository.findByName(name);
        return (List<Track>) trackByName;
    }

}
