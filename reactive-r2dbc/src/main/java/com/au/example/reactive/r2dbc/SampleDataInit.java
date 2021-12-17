package com.au.example.reactive.r2dbc;

import com.au.example.reactive.r2dbc.data.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author ayhanugurlu on 11/12/2021
 */
@Component
@RequiredArgsConstructor
public class SampleDataInit {

    private final ReservationRepository reservationRepository;

    private final DatabaseClient databaseClient;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        


        Flux<Reservation> reservations = Flux.just("ayhan", "mete", "orhan", "mehmet", "sly")
                .map(s -> new Reservation(null, s)).flatMap(this.reservationRepository::save);

        reservationRepository.deleteAll()
                .thenMany(reservations)
                .thenMany(reservationRepository.findAll())
                .subscribe(System.out::println);
    }

}
