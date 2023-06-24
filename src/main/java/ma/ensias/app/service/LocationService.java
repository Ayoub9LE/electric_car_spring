package ma.ensias.app.service;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import ma.ensias.app.entity.Location;
import ma.ensias.app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Objects;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteLocation(Long locationId) {
        if(!locationRepository.existsById(locationId)) {
            throw new IllegalStateException("Location with id "+locationId+" does not exist");
        }
        locationRepository.deleteById(locationId);
    }

    @Transactional
    public void updateLocation(Long locationId,
                               LocalDate startDate,
                               LocalDate endDate,
                               BigDecimal price,
                               Boolean isPaid) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new IllegalStateException(
                        "Location with id "+locationId+" does not exist"));

        if (startDate != null && !Objects.equals(location.getStartDate(), startDate)) {
            location.setStartDate(startDate);
        }

        if (endDate != null && !Objects.equals(location.getEndDate(), endDate)) {
            location.setEndDate(endDate);
        }


}

    public void updateLocation(Long id, LocalDate startDate, LocalDate endDate, Double price, Boolean paid) {
    }
}
