package com.stackroute.seedData;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerSeedData implements CommandLineRunner {

   private TrackRepository trackRepository;

   @Autowired
    public CommandLineRunnerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Track track1=new Track(1,"atif","nice");
        trackRepository.save(track1);
        Track track2=new Track(2,"shreya","good");
        trackRepository.save(track2);
    }
}
