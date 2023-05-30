package project.carbonFootprint.repo;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carbonFootprint.models.CarbonFootprintData;
import project.users.models.User;

@Repository

public interface CarbonFootprintDataRepository extends JpaRepository<CarbonFootprintData, Long> {
    public CarbonFootprintData findFirstByUser(User user, Sort sort);
}
