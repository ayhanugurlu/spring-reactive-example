package com.au.example.web;

import com.au.example.dto.ExampleRequest;
import com.au.example.dto.ExampleResponse;
import com.au.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author ayhanugurlu on 14/12/2021
 */
@RequiredArgsConstructor
@RestController
public class ExampleController {


    private final ExampleService exampleService;

    @GetMapping(path = "/examples/{name}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ExampleResponse> exampleFlux(@PathVariable String name){
        return exampleService.exampleMany(new ExampleRequest(name));
    }


    @GetMapping("/example/{name}")
    Mono<ExampleResponse> example(@PathVariable String name){
        return exampleService.exampleOnce(new ExampleRequest(name));
    }
}
