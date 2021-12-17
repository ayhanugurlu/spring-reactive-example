package com.au.example.config;

import com.au.example.dto.ExampleRequest;
import com.au.example.dto.ExampleResponse;
import com.au.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.Map;

/**
 * @author ayhanugurlu on 17/12/2021
 */

@Log4j2
@Configuration
@RequiredArgsConstructor
public class WebsocketConfig {

    private final ExampleService exampleService;

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of("/ws/examples", webSocketHandler), 10);
    }

    @Bean
    WebSocketHandler webSocketHandler() {
        return session -> {
//                Flux<WebSocketMessage> receive = session.receive();
//                Flux<String> names = receive.map(webSocketMessage -> webSocketMessage.getPayloadAsText());
//                Flux<ExampleRequest> requestFlux = names.map(s -> new ExampleRequest(s));
//                Flux<ExampleResponse> exampleResponseFlux = requestFlux.flatMap(exampleRequest -> exampleService.exampleMany(exampleRequest));
//                Flux<String> map = exampleResponseFlux.map(exampleResponse -> exampleResponse.getMessage());
//                Flux<WebSocketMessage> webSocketMessageFlux = map.map(s -> session.textMessage(s));
//                return session.send(webSocketMessageFlux);
            var receive = session.receive()
                    .map(webSocketMessage -> webSocketMessage.getPayloadAsText())
                    .map(ExampleRequest::new)
                    .flatMap(exampleService::exampleMany)
                    .map(ExampleResponse::getMessage)
                    .map(session::textMessage)
                    .doOnEach(signal -> log.info(signal.getType()))
                    .doFinally(signal -> log.info("finish" + signal.toString()));

            return session.send(receive);

        };
    }

}
