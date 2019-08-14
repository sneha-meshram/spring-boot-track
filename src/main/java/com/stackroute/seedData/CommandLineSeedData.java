package com.stackroute.seedData;


import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import lombok.extern.java.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//this class implements coomandlineRunner Interface
@Component
public class CommandLineSeedData implements CommandLineRunner {
    private TrackRepository trackRepository;
    //  protected final Log logger = (Log) LogFactory.getLog(getClass());

    //contructor of the class
    @Autowired
    public CommandLineSeedData(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Track track1 = new Track(1, "Real", "NF");


        //passing the track object
        trackRepository.save(track1);
        System.out.println("command line runner");
    }

}
