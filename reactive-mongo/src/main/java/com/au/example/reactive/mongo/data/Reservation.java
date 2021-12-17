package com.au.example.reactive.mongo.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ayhanugurlu on 11/12/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Reservation {

    @Id
    String id;

    String name;
}
