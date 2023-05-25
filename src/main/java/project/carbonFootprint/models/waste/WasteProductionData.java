package project.carbonFootprint.models.waste;

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
@Table(name = "WASTE_PRODUCTION_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class WasteProductionData {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carbon_footprint_id", nullable = false, referencedColumnName = "id")
    private CarbonFootprintData carbonFootprintData;
    @OneToMany(mappedBy = "wasteProductionData")
    private List<WasteProduction> wasteProductions;
}
