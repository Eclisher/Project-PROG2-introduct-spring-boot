package Repository.JDBC;

import Entities.Salle;
import Repository.SalleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcSalleRepository implements SalleRepository {

    @Override
    public List<Salle> findAll() {
        return null;
    }

    @Override
    public Salle findById(Long id) {
        return null;
    }

    @Override
    public Salle save(Salle salle) {

        return salle;
    }

    @Override
    public void update(Salle salle) {

    }

    @Override
    public boolean delete(Long id) {

        return false;
    }
}
