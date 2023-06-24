package ma.ensias.app.Repository;

import ma.ensias.app.entity.Location;
import ma.ensias.app.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void testFindLocationsByDateRange() {
        // Create test data
        Location location1 = new Location(LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 10), 100.0, true);
        locationRepository.save(location1);

        Location location2 = new Location(LocalDate.of(2023, 6, 5), LocalDate.of(2023, 6, 15), 150.0, false);
        locationRepository.save(location2);

        // Perform the repository method
        List<Location> foundLocations = locationRepository.findLocationsByDateRange(LocalDate.of(2023, 6, 3), LocalDate.of(2023, 6, 8));

        // Verify the results
        assertEquals(2, foundLocations.size());
        assertEquals(location1, foundLocations.get(0));
        assertEquals(location2, foundLocations.get(1));
    }
}
