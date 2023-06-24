package ma.ensias.app.service;



import java.util.List;

public class LocationService {

    @Inject
    private LocationRepository repository;

    public Location find(Long id) {
        return repository.find(id);
    }

    public Location save(Location location) {
        return repository.save(location);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public List<Location> findAll() {
        return repository.findAll();
    }
}

