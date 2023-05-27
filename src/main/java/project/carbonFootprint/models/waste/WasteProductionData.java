package project.carbonFootprint.models.waste;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.CarbonFootprintData;

import java.util.ArrayList;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carbon_footprint_id", nullable = false, referencedColumnName = "id")
    private CarbonFootprintData carbonFootprintData;
    @OneToMany(mappedBy = "wasteProductionData", cascade = CascadeType.ALL)
    private List<WasteProduction> wasteProductions;
    @Column
    private Double co2Emitted;
    @Column
    private Integer greenScore;

    public static WasteProductionData of(List<project.carbonFootprint.models.dto.carbonFootprintData.WasteProductionData> wasteProductionData){
        WasteProductionData response = WasteProductionData.builder().build();
        List<WasteProduction> wasteProductionList = new ArrayList<>();
        for(project.carbonFootprint.models.dto.carbonFootprintData.WasteProductionData data: wasteProductionData){
            wasteProductionList.add(WasteProduction.builder()
                            .wasteProductionData(response)
                            .consume(data.getProduction())
                            .wasteType(data.getWasteType())
                    .build());
        }
        response.setWasteProductions(wasteProductionList);
        return response;
    }
}
