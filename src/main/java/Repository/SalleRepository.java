package Repository;

import Entities.Salle;

import java.util.List;

public interface SalleRepository {
    List<Salle> findAll();
    Salle findById(Long id);
    Salle save(Salle salle);
    void update(Salle salle);
    boolean delete (Long id);

}
