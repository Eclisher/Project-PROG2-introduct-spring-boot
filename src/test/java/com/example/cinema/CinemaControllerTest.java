package com.example.cinema;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.cinema.Model.*;
import com.example.cinema.Service.CinemaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.cinema.Controller.CinemaController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
@WebMvcTest(CinemaController.class)
public class CinemaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CinemaService cinemaService;

    @Test
    public void testGetAllClients() throws Exception {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client(1L, "Lucrèce", "Mufinella", "melis0@google.cn"));
        clients.add(new Client(2L, "Valérie", "Genni", "gpurvey1@businessinsider.com"));

        when(cinemaService.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/clients"))
                .andExpectAll(status().isOk())
                .andExpectAll(jsonPath("$[0].nom").value("Lucrèce"))
                .andExpect(jsonPath("$[1].nom").value("Valérie"));

    }

}
