package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setup() {
        track = new Track();
        track.setId(11);
        track.setName("lalala");
        track.setComment("nice song");
//        list = new ArrayList();
//        list.add(track);
    }

    @After
    public void tearDown() {
        trackRepository.deleteAll();
    }

    //test case for save track success.
    @Test
    public void testSaveTrack() {
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(11, fetchTrack.getId());
    }


    //test case for save track failure.
    @Test
    public void testSaveTrackFailure(){
       Track testTrack=new Track(3,"samjhawan","By Rahat");
       trackRepository.save(track);
       Track fetchTrack=trackRepository.findById(track.getId()).get();
       Assert.assertNotEquals(testTrack,track);
    }


    //test case for given track should return All track.
    @Test
    public void testGetAllTrack(){
        Track t=new Track(4,"photograph","Ed sheeran");
        Track t1=new Track(5,"theMiddle","Zedd");
        trackRepository.save(t);
        trackRepository.save(t1);
        List<Track> list=trackRepository.findAll();
        Assert.assertEquals("photograph",list.get(0).getName());
    }

    //test case for returning track by name.
    @Test
    public void testGetTrackByName(){
        Track t=new Track(4,"perfect","Ed sheeran");
        Track t1=new Track(5,"perfect","Zedd");
        Track t3=new Track(5,"dive","zayn");
        trackRepository.save(t);
        trackRepository.save(t1);
        trackRepository.save(t3);
        List<Track> list=trackRepository.findAll();
        Assert.assertEquals("perfect",list.get(0).getName());
    }

    //test case for should not returning track by name.
    @Test
    public void testForShouldNotReturnTrackByName(){
        Track t=new Track(4,"perfect","Ed sheeran");
        Track t1=new Track(5,"perfect","Zedd");
        Track t3=new Track(5,"dive","zayn");
        trackRepository.save(t);
        trackRepository.save(t1);
        trackRepository.save(t3);
        List<Track> list=trackRepository.findAll();
        Assert.assertNotEquals("perfect",list);
    }


}