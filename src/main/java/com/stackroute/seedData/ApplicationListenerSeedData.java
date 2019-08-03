package com.stackroute.seedData;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerSeedData implements ApplicationListener {
    private TrackRepository trackRepository;
    @Value("${track1.id}")
    private int id;
    @Value("${track1.name}")
    private String name;
    @Value("${track1.comment}")
    private String comment;

    Track track1 = new Track();


    @Autowired
    public ApplicationListenerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        track1.setId(id);
        track1.setName(name);
        track1.setComment(comment);
        trackRepository.save(track1);
    }

}
