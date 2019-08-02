package com.stackroute.controller;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class TrackController {

    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> save(@RequestBody Track track) {
        ResponseEntity entity;
        try {
            trackService.save(track);
            entity = new ResponseEntity<String>("Success", HttpStatus.OK);
        } catch (TrackAlreadyExistsException e) {
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return entity;
    }


    @GetMapping("/track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            trackService.getTrackById(id);
            responseEntity = new ResponseEntity<String>("Ok Got it", HttpStatus.OK);

        } catch (TrackNotFound e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/tracks")
    public ResponseEntity<?> getAllTrack(@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            List<Track> tracks = trackService.getAllTrack();
            responseEntity = new ResponseEntity<String>("all Track found", HttpStatus.OK);
        } catch (TrackNotFound e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        }
        return responseEntity;
    }

    @DeleteMapping("/track/{id}")
    public ResponseEntity deleteTrackById(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            Optional<Track> sendDeleteTrack = trackService.deleteTrackById(id);
            return new ResponseEntity(sendDeleteTrack, HttpStatus.OK);
        } catch (TrackNotFound e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    //method for getting track by id

    @PatchMapping("/track/{id}")
    public ResponseEntity updateTrackById(@PathVariable int id) {
        Optional<Track> sendUpdateTrack = trackService.updateTrackById(id);
        return new ResponseEntity(sendUpdateTrack, HttpStatus.OK);
    }

    //method for get Track by Name

    @GetMapping("/tracks/{name}")
    public ResponseEntity getTrackByName(@PathVariable String name) {
        ResponseEntity responseEntity;

        try {
            List<Track> sendTrackByName = trackService.gettrackByName(name);
            return new ResponseEntity(sendTrackByName, HttpStatus.OK);
        } catch (TrackNotFound e) {

            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }
}
