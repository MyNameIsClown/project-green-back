package project.carbonFootprint.models.dto.detail;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.dto.carbonFootprintData.EnergyConsumptionData;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class EnergyData {
    private Long id;
    private List<EnergyConsumptionData> energyConsumptionData;
    private Integer co2Emitted;
    private Integer greenScore;

    public static EnergyData of (project.carbonFootprint.models.energy.EnergyConsumptionData energyConsumptionData){
        return EnergyData.builder()
                .id(energyConsumptionData.getId())
                .energyConsumptionData(energyConsumptionData.getEnergyConsumptions().stream().map(
                        (value) -> EnergyConsumptionData.builder().energyType(value.getEnergyType()).consume(value.getConsume()).build()
                ).collect(Collectors.toList()))
                .co2Emitted(energyConsumptionData.getCo2Emitted())
                .greenScore(energyConsumptionData.getGreenScore())
                .build();
    }
}
