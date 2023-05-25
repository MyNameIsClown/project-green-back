package project.carbonFootprint.models.transportation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.CarbonFootprintData;

import java.util.List;

@Entity
@Table(name = "TRANSPORTATION_USE_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class TransportationUseData {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carbon_footprint_id", nullable = false, referencedColumnName = "id")
    private CarbonFootprintData carbonFootprintData;
    @OneToMany(mappedBy = "transportationUseData")
    private List<TransportationUse> transportationUses;
}
