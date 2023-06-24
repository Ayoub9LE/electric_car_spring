package ma.ensias.app.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
import ma.ensias.app.entity.Location;

import java.util.List;

public class LocationRepository {
    @PersistenceContext
    private EntityManager em;

    public Location find(Long id) {
        return em.find(Location.class, id);
    }

    @Transactional
    public Location save(Location location) {
        if (location.getId() == null) {
            em.persist(location);
            return location;
        } else {
            return em.merge(location);
        }
    }

    @Transactional
    public void delete(Long id) {
        Location location = em.find(Location.class, id);
        if (location != null) {
            em.remove(location);
        }
    }

    public List<Location> findAll() {
        return em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
    }
}

