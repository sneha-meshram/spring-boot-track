package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Profile("production")
@Service
@Primary
public class TrackServiceImpl implements TrackService {
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track save(Track track) throws TrackAlreadyExistsException {

        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("track already exist");
        }
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFound {
        if (trackRepository.findByName(name).isEmpty()) {
            throw new TrackNotFound("track not found");
        } else
            return trackRepository.findByName(name);
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFound {

        if (trackRepository.existsById(id)) {
            Track track = trackRepository.findById(id).get();
            return track;
        } else {
            throw new TrackNotFound("track not found");
        }
    }

    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }


    @Override
    public Optional<Track> deleteTrackById(int id) throws TrackNotFound {

        Optional<Track> optionalTrack = trackRepository.findById(id);
        if (optionalTrack.isPresent()) {
            trackRepository.deleteById(id);
            return optionalTrack;
        } else
            throw new TrackNotFound("track not found");
    }

    @Override
    public Optional<Track> updateTrackById(int id) {
        Optional<Track> optionalUpdate = updateTrackById(id);
        if (optionalUpdate.isPresent()) {
            Track track1 = updateTrackById(id).get();
        }
        return optionalUpdate;
    }

}
