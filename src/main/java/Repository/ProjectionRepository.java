package Repository;

import Entities.Projection;

import java.util.List;

public interface ProjectionRepository {
    List <Projection> findALl();
    Projection findById(Long id);
    Projection save (Projection projection);
    void update (Projection projection);
    boolean delete(Long id);

}
