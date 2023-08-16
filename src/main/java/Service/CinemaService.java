package Service;

import Entities.Client;
import Entities.Film;
import Entities.Projection;
import Entities.Salle;
import Repository.ClientRepository;
import Repository.FilmRepository;
import Repository.ProjectionRepository;
import Repository.SalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {
    private final ClientRepository clientRepository;
    private final SalleRepository salleRepository;
    private final ProjectionRepository projectionRepository;
    private final FilmRepository filmRepository;

    public CinemaService(ClientRepository clientRepository, SalleRepository salleRepository,
                         ProjectionRepository projectionRepository, FilmRepository filmRepository) {
        this.clientRepository = clientRepository;
        this.salleRepository = salleRepository;
        this.projectionRepository = projectionRepository;
        this.filmRepository = filmRepository;
    }

    // Méthodes pour la gestion des clients

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id);
        if (existingClient != null) {
            existingClient.setNom(client.getNom()); // Mettez à jour d'autres propriétés selon votre modèle
            return clientRepository.save(existingClient);
        }
        return null;
    }

    public boolean deleteClient(Long id) {
        return clientRepository.delete(id);
    }

    // Méthodes pour la gestion des salles

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Salle getSalleById(Long id) {
        return salleRepository.findById(id);
    }

    public Salle createSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    public Salle updateSalle(Long id, Salle salle) {
        Salle existingSalle = salleRepository.findById(id);
        if (existingSalle != null) {
            existingSalle.setNom(salle.getNom()); // Mettez à jour d'autres propriétés selon votre modèle
            return salleRepository.save(existingSalle);
        }
        return null;
    }

    public boolean deleteSalle(Long id) {
        return salleRepository.delete(id);
    }
    public List<Projection> getAllProjections() {
        return projectionRepository.findALl();
    }

    public Projection getProjectionById(Long id) {
        return projectionRepository.findById(id);
    }

    public Projection createProjection(Projection projection) {
        return projectionRepository.save(projection);
    }

    public Projection updateProjection(Long id, Projection projection) {
        Projection existingProjection = projectionRepository.findById(id);
        if (existingProjection != null) {
            existingProjection.setDateHeure(projection.getDateHeure()); // Mettez à jour d'autres propriétés selon votre modèle
            return projectionRepository.save(existingProjection);
        }
        return null;
    }

    public boolean deleteProjection(Long id) {
        return projectionRepository.delete(id);
    }

    // Méthodes pour la gestion des films

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id);
    }

    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film film) {
        Film existingFilm = filmRepository.findById(id);
        if (existingFilm != null) {
            existingFilm.setTitre(film.getTitre()); // Mettez à jour d'autres propriétés selon votre modèle
            return filmRepository.save(existingFilm);
        }
        return null;
    }

    public boolean deleteFilm(Long id) {
        return filmRepository.delete(id);
    }
}
