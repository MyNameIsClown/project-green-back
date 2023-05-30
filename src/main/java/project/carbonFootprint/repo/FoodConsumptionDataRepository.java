package project.carbonFootprint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carbonFootprint.models.food.FoodConsumptionData;
@Repository
public interface FoodConsumptionDataRepository extends JpaRepository<FoodConsumptionData, Long> {
}
