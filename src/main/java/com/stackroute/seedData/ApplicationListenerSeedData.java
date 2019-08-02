package com.stackroute.seedData;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerSeedData implements ApplicationListener {


    private TrackRepository trackRepository;

    @Autowired
    public ApplicationListenerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        Track track1=new Track(1,"atif","nice");
        trackRepository.save(track1);
        Track track2=new Track(2,"shreya","good");
        trackRepository.save(track2);
    }
}
