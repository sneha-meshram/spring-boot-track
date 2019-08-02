package com.stackroute.controller;


import com.stackroute.domain.Track;
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
        System.out.println(track);
        Track sendTrack = trackService.save(track);
        return new ResponseEntity<>(sendTrack, HttpStatus.ACCEPTED);
    }


    @GetMapping("/track/{id}")
    public ResponseEntity<?>getTrackById(@PathVariable int id){
        Track sendTrackById=trackService.getTrackById(id);
        return new ResponseEntity<>(sendTrackById, HttpStatus.ACCEPTED);
    }

//    @PostMapping("/allTrack")
//    public ResponseEntity<?>getAllTrack(@RequestBody Track track){
//        Track sendAllTrack = trackService.save(track);
//        return new ResponseEntity<>(sendAllTrack, HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping("/track/{id}")
//    public ResponseEntity deleteTrackById(@PathVariable int id){
//        Optional<Track> sendDeleteTrack=trackService.deleteTrackById(id);
//        return new ResponseEntity(sendDeleteTrack, HttpStatus.OK);
//
//    }


}
