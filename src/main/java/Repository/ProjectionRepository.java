package Repository;

import Entities.Projection;

import java.util.List;

public interface ProjectionRepository {
    List <Projection> findALl();
    Projection findById(Long id);
    void save (Projection projection);
    void update (Projection projection);
    void delete(Long id);

}
