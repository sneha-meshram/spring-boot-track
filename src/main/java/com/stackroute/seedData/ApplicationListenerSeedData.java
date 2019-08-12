package com.stackroute.seedData;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;



//this class implements Application Listener Interface
@Component
public class ApplicationListenerSeedData implements ApplicationListener<ContextRefreshedEvent> {
    private TrackRepository trackRepository;

    //contructor of class ApplicationListen
    @Autowired
    public ApplicationListenerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //onApplicationEvent method i made a object of track and added the values and save it will automatically save
    // to database runtime
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Application Event running");
        Track track1 = new Track(254, "abcccc", "hjjh");


        //passing the track object
        trackRepository.save(track1);

    }
}