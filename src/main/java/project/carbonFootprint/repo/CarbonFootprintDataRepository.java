package project.carbonFootprint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carbonFootprint.models.CarbonFootprintData;
@Repository

public interface CarbonFootprintDataRepository extends JpaRepository<CarbonFootprintData, Long> {
}
