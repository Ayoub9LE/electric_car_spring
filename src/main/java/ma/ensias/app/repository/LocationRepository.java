package ma.ensias.app.repository;

import ma.ensias.app.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.startDate >= ?1 and l.endDate <= ?2")
    List<Location> findLocationsByDateRange(LocalDate startDate, LocalDate endDate);
}