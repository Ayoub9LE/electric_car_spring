package ma.ensias.app.controller;

import ma.ensias.app.entity.Agency;
import ma.ensias.app.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agencies")
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping
    public List<Agency> getAllAgencies() {
        return agencyService.getAllAgencies();
    }

    @GetMapping("/{id}")
    public Agency getAgencyById(@PathVariable Long id) {
        return agencyService.getAgencyById(id).orElseThrow(() -> new RuntimeException("Agency not found"));
    }

    @PostMapping
    public Agency createAgency(@RequestBody Agency agency) {
        return agencyService.createAgency(agency);
    }

    @PutMapping("/{id}")
    public Agency updateAgency(@PathVariable Long id, @RequestBody Agency agency) {
        // You might want to validate or set the id from the path into the agency object here
        return agencyService.updateAgency(agency);
    }

    @DeleteMapping("/{id}")
    public void deleteAgency(@PathVariable Long id) {
        agencyService.deleteAgency(id);
    }
}
