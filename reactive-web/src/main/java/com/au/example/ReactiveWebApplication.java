package com.au.example;

import com.au.example.dto.ExampleRequest;
import com.au.example.dto.ExampleResponse;
import com.au.example.service.ExampleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ReactiveWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveWebApplication.class, args);
    }


    @Bean
    RouterFunction<ServerResponse> routes(ExampleService exampleService) {
        RouterFunctions.Builder route = route();
        route.GET("/examplereactive/{name}", request -> {
            return ServerResponse.ok()
                    .body(exampleService.exampleOnce(new ExampleRequest(request.pathVariable("name"))), ExampleResponse.class);
        });
        route.GET("/examplereactives/{name}", request -> {
            return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(exampleService.exampleMany(new ExampleRequest(request.pathVariable("name"))), ExampleResponse.class);
        });
        return route.build();
    }

}
