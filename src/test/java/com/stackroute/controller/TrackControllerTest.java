package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.exceptions.GlobalException;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFound;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

//import static jdk.internal.vm.compiler.word.LocationIdentity.any;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list = null;

    @Before
    public void setup() throws Exception {
//        Initialising the mock object
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController)
                .setControllerAdvice(new GlobalException()).build();
        track = new Track();
        track.setId(11);
        track.setName("lalala");
        track.setComment("nice song");
        list = new ArrayList();
        list.add(track);
    }


    @After
    public void tearDown() {
        track = null;
    }

    //testcase for success of save method.
    @Test
    public void givenTrackShouldReturnsaveTrack() throws Exception {
        when(trackService.save(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).save(track);
    }

    //testcase for failure of save method.
    @Test
    public void givenTrackShouldReturnsaveTrackFailure() throws Exception {
        when(trackService.save(track)).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    //testcase for getAllTrack method.
    @Test
    public void givenTrackShouldReturngetAllTrack() throws Exception {
        when(trackService.getAllTrack()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).getAllTrack();
    }


    //testcase for UpdateById success
    @Test
    public void givenTrackShouldReturnUpdatedTrack() throws Exception {
        when(trackService.updateTrackById(anyInt())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/track/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).updateTrackById(track.getId());
    }

   // testcase for UpdateTrackById failure
    @Test
    public void givenTrackShouldReturnTrackNotFoundException() throws Exception {
        when(trackService.updateTrackById(anyInt())).thenThrow(TrackNotFound.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/11")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).updateTrackById(track.getId());
    }




    //testcase for DeleteTrackById success
    @Test
    public void givenTrackIdShouldReturnDeletedTrack() throws Exception {
        when(trackService.deleteTrackById(anyInt())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).deleteTrackById(track.getId());
    }

    //    delete track by id failure
    @Test
    public void givenNonExistTrackIdShouldThrowTrackNotFoundException() throws Exception {
        when(trackService.deleteTrackById(anyInt())).thenThrow(TrackNotFound.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/11")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).deleteTrackById(track.getId());
    }

    //    get track by name success
    @Test
    public void givenTrackNameShouldReturnTrackDetails() throws Exception {
        trackService.save(track);
        when(trackService.getTrackByName("lalala")).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/name/lalala")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.content().json(asJsonString(track)))
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).getTrackByName(track.getName());
    }




    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}