package com.au.example.reactive.mongo.repository;


import com.au.example.reactive.mongo.data.Reservation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author ayhanugurlu on 11/12/2021
 */

public interface ReservationRepository extends ReactiveCrudRepository<Reservation, String> {
}
