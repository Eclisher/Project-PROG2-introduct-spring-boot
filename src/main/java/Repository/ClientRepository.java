package Repository;

import Entities.Client;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();

    Client findById(Long id);

    void save(Client client);

    void update(Client client);

    void delete(Long id);
}
