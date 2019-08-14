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
    public Track getTrackByName(String name) throws TrackNotFound {
        if (trackRepository.findByName(name).isEmpty()) {
            throw new TrackNotFound("track not found");
        } else
            return (Track) trackRepository.findByName(name);
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
    public Track updateTrackById(int id, Track track) {
        //        delete the track
        trackRepository.deleteById(id);
        // edit the track and save it
        Track updateTrack = trackRepository.save(track);
        return updateTrack;
    }

    @Override
    public List<Track> getAllTrack() {
        return trackRepository.findAll();
    }

    @Override
    public Track deleteTrackById(int id) throws Exception {
        if (trackRepository.existsById((id))) {
            Track getTrack = trackRepository.findById(id).get();
            return getTrack;
        } else {
            throw new Exception("Track not found for deletion");
        }
    }

}
