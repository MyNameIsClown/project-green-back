package project.carbonFootprint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carbonFootprint.models.waste.WasteProductionData;

@Repository
public interface WasteProductionDataRepository extends JpaRepository<WasteProductionData, Long> {
}
