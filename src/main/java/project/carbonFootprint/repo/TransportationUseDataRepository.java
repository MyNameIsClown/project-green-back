package project.carbonFootprint.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carbonFootprint.models.transportation.TransportationUseData;
@Repository
public interface TransportationUseDataRepository extends JpaRepository<TransportationUseData, Long> {
}
