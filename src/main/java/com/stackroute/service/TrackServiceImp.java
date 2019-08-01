package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImp implements TrackService {
    private TrackRepository trackRepository;

    public TrackServiceImp() {
    }

    public TrackServiceImp(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track save(Track track) {
        Track savedTrack = trackRepository.save(track);

        return savedTrack;
    }

    @Override
    public Track getTrackById(int id) {
        Track getTrackById = trackRepository.findById(id).get();
        return getTrackById;
    }

    @Override
    public List<Track> getAllTrack(Track track) {
        return trackRepository.findAll();
    }

    @Override
    public Optional<Track> deleteTrackById(int id) {
        Optional<Track> optionalTrack = deleteTrackById(id);
        if (optionalTrack.isPresent()) {
            Track track = deleteTrackById(id).get();
        }
        return optionalTrack;
    }

    @Override
    public Track updateTrackById(int id) {
        return null;
    }


}
