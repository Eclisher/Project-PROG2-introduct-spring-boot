package Repository;

import Entities.Salle;

import java.util.List;

public interface SalleRepository {
    List<Salle> findAll();
    Salle findById(Long id);
    void save(Salle salle);
    void update(Salle salle);
    void delete (Long id);

}
