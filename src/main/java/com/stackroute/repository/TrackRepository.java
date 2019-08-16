package com.stackroute.repository;


import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    //query for getting Track by name.
//
//    @Query("select t from Track t where t.name=?1")
//    List<Track> findByName(String name);

}
