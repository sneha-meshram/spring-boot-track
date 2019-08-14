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

    //contructor of class ApplicationListener.
    @Autowired
    public ApplicationListenerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Application running");
        Track track1 = new Track(254, "taki", "selena");


        //passing the track object
        trackRepository.save(track1);

    }
}