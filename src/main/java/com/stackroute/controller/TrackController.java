package com.stackroute.controller;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.service.TrackService;
import com.stackroute.service.TrackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class TrackController {

    private TrackService trackService;

    public TrackController() {
    }

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


    @PostMapping("track")
    public ResponseEntity<?> save(@RequestBody Track track) throws Exception {
        Track savedTrack = trackService.save(track);
        return new ResponseEntity<Track>(savedTrack, HttpStatus.CREATED);
    }


    @GetMapping("/track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws Exception {
        ResponseEntity responseEntity;
        trackService.getTrackById(id);
        responseEntity = new ResponseEntity<String>("Ok Got it", HttpStatus.OK);
        //  responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @GetMapping("/tracks")
    public ResponseEntity<?> getAllTrack(@RequestBody Track track) throws Exception {
        ResponseEntity responseEntity;
        List<Track> tracks = trackService.getAllTrack();
        responseEntity = new ResponseEntity<String>("all Track found", HttpStatus.OK);
        return responseEntity;
    }


    //    to delete a track by its id
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id) throws Exception {
        ResponseEntity responseEntity;
        Track trackRemoved = trackService.deleteTrackById(id);
        responseEntity = new ResponseEntity<>(trackRemoved, HttpStatus.NO_CONTENT);
        return responseEntity;
    }
    //method for update track by id
     @PatchMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable int id, @RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            trackService.updateTrackById(id, track);
            responseEntity = new ResponseEntity(track, HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //method for get Track by Name

    @GetMapping("/track/name/{name}")
    public ResponseEntity<Track> getTrackByName(@PathVariable String name) throws Exception {
        ResponseEntity responseEntity;
        Track sendTrackByName = trackService.getTrackByName(name);
        return new ResponseEntity(sendTrackByName, HttpStatus.FOUND);

        //  responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        //return responseEntity;
    }
}

