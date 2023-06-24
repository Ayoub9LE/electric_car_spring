package ma.ensias.app.controller;


import ma.ensias.app.entity.Location;
import ma.ensias.app.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        locationService.addLocation(location);
        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        locationService.updateLocation(id, location.getStartDate(), location.getEndDate(),
                location.getPrice(), location.getPaid());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
