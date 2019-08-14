package com.stackroute.service;
//
//import com.stackroute.domain.Track;
//import com.stackroute.exceptions.TrackAlreadyExistsException;
//import com.stackroute.repository.TrackRepository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static jdk.internal.vm.compiler.word.LocationIdentity.any;
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
public class TrackServiceTest {

    private Track track;
    //Create a mock for TrackRepository
    @Mock
    private TrackRepository trackRepository;
    //Inject the mocks as dependencies into TrackServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(12);
        track.setName("BlankSpace");
        track.setComment("TaylorSwift");
        list = new ArrayList<>();
        list.add(track);
    }

    @After
    public void tearDown() {
        list = null;
        track = null;
    }

    //given track should return the save Track .
    @Test
    public void givenTrackShouldReturnsaveTrack() throws Exception {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track saveTrack = trackRepository.save(track);
        Assert.assertEquals(track, saveTrack);
       verify(trackRepository, times(1)).save(track);

    }

    //given track should not return the save track.
    @Test(expected = TrackAlreadyExistsException.class)
    public void givenTrackShouldsaveTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        Track savedTrack = trackService.save(track);
        Assert.assertEquals(track,savedTrack);

    }

    //given track should return all the track list.
    @Test
    public void getAllTrack() {
        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracks = trackRepository.findAll();
        Assert.assertEquals(list, tracks);
    }
    @Test
    public void deleteTrackById() {
    }

    @Test
    public void givenIdShouldReturnDeletedTrack() throws Exception {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track savedTrack = trackService.deleteTrackById(track.getId());
        assertEquals(track, savedTrack);
       // verify(trackRepository, times(1)).save(track);
    }


    @Test
    public void updateTrackById() {
    }

    @Test
    public void getTrackByName() {
    }
}