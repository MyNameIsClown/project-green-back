package project.carbonFootprint.models.dto.detail;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.dto.carbonFootprintData.WasteProductionData;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class WasteData {
    private Long id;
    private List<WasteProductionData> wasteProductionData;
    private Integer co2Emitted;
    private Integer greenScore;

    public static WasteData of (project.carbonFootprint.models.waste.WasteProductionData wasteData){
        return WasteData.builder()
                .id(wasteData.getId())
                .wasteProductionData(wasteData.getWasteProductions().stream().map(
                        (value) -> WasteProductionData.builder()
                                .wasteType(value.getWasteType())
                                .production(value.getConsume())
                                .build()
                ).collect(Collectors.toList()))
                .co2Emitted(wasteData.getCo2Emitted())
                .greenScore(wasteData.getGreenScore())
                .build();
    }
}
