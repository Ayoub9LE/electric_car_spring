package ma.ensias.app.service;

import ma.ensias.app.entity.Agency;
import ma.ensias.app.repository.AgencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;


@Service
public class AgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }

    public Optional<Agency> getAgencyById(Long id) {
        return agencyRepository.findById(id);
    }

    public Agency createAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    public Agency updateAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    public void deleteAgency(Long id) {
        agencyRepository.deleteById(id);
    }
}

