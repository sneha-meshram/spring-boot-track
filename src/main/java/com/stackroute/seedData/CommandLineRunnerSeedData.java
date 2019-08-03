package com.stackroute.seedData;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerSeedData implements CommandLineRunner {

    private TrackRepository trackRepository;

    @Value("${track2.id}")
    private int id;
    @Value("${track2.name}")
    private String name;
    @Value("${track2.comment}")
    private String comment;
    Track track2 = new Track();

    @Autowired
    public CommandLineRunnerSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        track2.setId(id);
        track2.setName(name);
        track2.setComment(comment);
        trackRepository.save(track2);
    }
}
