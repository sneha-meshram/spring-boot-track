package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Profile("dev")
@Service
//@Qualifier("dummy")
public class TrackDummyServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    public TrackDummyServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //return all save track.
    @Override
    public Track save(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("track already exist");
        }
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    //return track which is given by track id.
    @Override
    public Track getTrackById(int id) throws TrackNotFound {

        if (trackRepository.existsById(id)) {
            Track track = trackRepository.findById(id).get();
            return track;
        } else {
            throw new TrackNotFound("track not found");
        }
    }

    //return list of all track.
    @Override
    public List<Track> getAllTrack() throws TrackNotFound {
        return trackRepository.findAll();
    }
    //delete track which are given by track id.

    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFound {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        if (optionalTrack.isPresent()) {
            trackRepository.deleteById(id);
            return optionalTrack;
        } else
            throw new TrackNotFound("track not found");
    }

    //update track which are given by track id.
    @Override
    public Optional<Track> updateTrackById(int id) {
        Optional<Track> optionalUpdate = updateTrackById(id);
        if (optionalUpdate.isPresent()) {
            Track track1 = updateTrackById(id).get();
        }
        return optionalUpdate;
    }

    //return track by name.
    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFound {
        if (trackRepository.findByName(name).isEmpty()) {
            throw new TrackNotFound("track not found");
        } else
            return trackRepository.findByName(name);
    }
}
