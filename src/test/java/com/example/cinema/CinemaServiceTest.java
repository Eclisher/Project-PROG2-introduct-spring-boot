package com.example.cinema;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.cinema.Service.CinemaService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.cinema.Model.*;
import com.example.cinema.Repository.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class CinemaServiceTest {

    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final SalleRepository salleRepository = mock(SalleRepository.class);
    private final ProjectionRepository projectionRepository = mock(ProjectionRepository.class);
    private final FilmRepository filmRepository = mock(FilmRepository.class);
    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final CinemaService cinemaService = new CinemaService(clientRepository, salleRepository, projectionRepository, filmRepository, reservationRepository);

    @Test
    public void testGetAllClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, "Lucrèce", "Mufinella", "melis0@google.cn"));
        clients.add(new Client(2L, "Valérie", "Genni", "gpurvey1@businessinsider.com"));

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = cinemaService.getAllClients();

        assertEquals(2, result.size());
    }
}
