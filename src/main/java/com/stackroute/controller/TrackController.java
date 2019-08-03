package com.stackroute.controller;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.service.TrackService;
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

    TrackService trackService;

    @Autowired
    public TrackController(@Qualifier("dummy") TrackService trackService)
    {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> save(@RequestBody Track track) throws Exception {
        ResponseEntity responseEntity;
        trackService.save(track);
        responseEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
        //   entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        return responseEntity;
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
        //    responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/track/{id}")
    public ResponseEntity deleteTrackById(@PathVariable int id)throws Exception {
        ResponseEntity responseEntity;
            Optional<Track> sendDeleteTrack = trackService.deleteTrackById(id);
            return new ResponseEntity(sendDeleteTrack, HttpStatus.OK);
           // responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
            //return responseEntity;
    }
    //method for getting track by id

    @PatchMapping("/track/{id}")
    public ResponseEntity updateTrackById(@PathVariable int id) {
        Optional<Track> sendUpdateTrack = trackService.updateTrackById(id);
        return new ResponseEntity(sendUpdateTrack, HttpStatus.OK);
    }

    //method for get Track by Name

    @GetMapping("/tracks/{name}")
    public ResponseEntity<Track> getTrackByName(@PathVariable String name)throws Exception{
        ResponseEntity responseEntity;
            List<Track> sendTrackByName = trackService.getTrackByName(name);
            return new ResponseEntity(sendTrackByName, HttpStatus.OK);

          //  responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        //return responseEntity;
    }
}

