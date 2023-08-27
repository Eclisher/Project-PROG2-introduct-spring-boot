package com.example.cinema;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.cinema.Model.*;
import com.example.cinema.Service.CinemaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.cinema.Controller.CinemaController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @Test
    public void testCreateClient() throws Exception {
        Client newClient = new Client(31L, "Doe", "john", "hei.jonh@gmail.com");
        newClient.setNom("John");
        newClient.setPrenom("Doe");
        newClient.setEmail("john@example.com");

        when(cinemaService.createClient(any())).thenReturn(newClient);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newClient))) // Convert the newClient to JSON string
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("John"))
                .andExpect(jsonPath("$.prenom").value("Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @InjectMocks
    private CinemaController cinemaController;

    @Test
    public void testUpdateClient() throws Exception {
        Client updatedClient = new Client(1L, "Nouveau", "Nom", "nouveau@email.com");

        when(cinemaService.updateClient(eq(1L), any(Client.class)))
                .thenReturn(updatedClient);

        mockMvc.perform(put("/clients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Nouveau"))
                .andExpect(jsonPath("$.prenom").value("Nom"))
                .andExpect(jsonPath("$.email").value("nouveau@email.com"));

        verify(cinemaService).updateClient(eq(1L), any(Client.class));
    }

    @Test
    public void testDeleteClient() throws Exception {
        when(cinemaService.deleteClient(1L))
                .thenReturn(true);

        mockMvc.perform(delete("/clients/1"))
                .andExpect(status().isOk());

        verify(cinemaService).deleteClient(1L);
    }

}
