package project.carbonFootprint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carbonFootprint.models.CarbonFootprintCalcParameter;

@Repository
public interface CarbonFooprintCalcParameterRepository extends JpaRepository<CarbonFootprintCalcParameter, Long> {
    CarbonFootprintCalcParameter findByName(String name);
}
