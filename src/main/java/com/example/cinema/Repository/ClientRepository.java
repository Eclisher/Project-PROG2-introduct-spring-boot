package com.example.cinema.Repository;

import com.example.cinema.Model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository {
    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);

    void update(Client client);

    boolean delete(Long id);
}
