package com.au.example.service;

import com.au.example.dto.ExampleRequest;
import com.au.example.dto.ExampleResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

/**
 * @author ayhanugurlu on 14/12/2021
 */
@Service
public class ExampleService {


    private ExampleResponse example(ExampleRequest  exampleRequest){
        return new ExampleResponse("Example "+exampleRequest.getName()+ "@ "+ Instant.now());
    }


    public Flux<ExampleResponse> exampleMany(ExampleRequest  exampleRequest){
        return Flux.fromStream(Stream.generate(() -> example(exampleRequest)))
                .delayElements(Duration.ofSeconds(1));
    }

}
