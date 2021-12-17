package com.au.example.reactive.mongo;//package com.au.example.reactive.mongo;

import com.au.example.reactive.mongo.data.Reservation;
import com.au.example.reactive.mongo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author ayhanugurlu on 11/12/2021
 */
@Component("sampleDataInitMongo")
@RequiredArgsConstructor
public class SampleDataInit {

    private final ReservationRepository reservationRepository;

    //@EventListener(ApplicationReadyEvent.class)
    public void init() {
        Flux<Reservation> reservations = Flux.just("ayhan", "mete", "orhan", "mehmet", "sly")
                .map(s -> new Reservation(null, s)).flatMap(this.reservationRepository::save);

        reservationRepository.deleteAll()
                .thenMany(reservations)
                .thenMany(reservationRepository.findAll())
                .subscribe(System.out::println);
    }

}
