package com.au.example.reactive.r2dbc.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author ayhanugurlu on 11/12/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    Integer id;

    String name;
}
