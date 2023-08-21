package com.example.cinema.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Reservation {
    private Long id;
    private Long client;
    private Long projection;
    private LocalDateTime reservationDate;

    public Reservation(Long id, Long client, Long projection, LocalDateTime reservationDate) {
        this.id = id;
        this.client = client;
        this.projection = projection;
        this.reservationDate = reservationDate;
    }

}
