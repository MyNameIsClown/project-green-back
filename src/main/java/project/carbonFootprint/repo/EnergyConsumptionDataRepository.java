package project.carbonFootprint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carbonFootprint.models.energy.EnergyConsumptionData;
@Repository
public interface EnergyConsumptionDataRepository extends JpaRepository<EnergyConsumptionData, Long> {
}
