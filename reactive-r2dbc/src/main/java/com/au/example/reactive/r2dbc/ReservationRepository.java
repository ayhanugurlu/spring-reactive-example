package com.au.example.reactive.r2dbc;

import com.au.example.reactive.r2dbc.data.Reservation;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author ayhanugurlu on 11/12/2021
 */

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, Integer> {

    @Query("select * from reservation where name = $1 ")
    Flux<Reservation>  findByName(String name);
}
