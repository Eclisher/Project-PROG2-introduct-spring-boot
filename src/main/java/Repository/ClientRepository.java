package Repository;

import Entities.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);

    void update(Client client);

    boolean delete(Long id);
}
