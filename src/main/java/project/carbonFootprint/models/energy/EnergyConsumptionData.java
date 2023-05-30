package project.carbonFootprint.models.energy;

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
@Table(name = "ENERGY_CONSUMPTION_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class EnergyConsumptionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carbon_footprint_id", nullable = false, referencedColumnName = "id")
    private CarbonFootprintData carbonFootprintData;
    @OneToMany(mappedBy = "energyConsumptionData", cascade = CascadeType.ALL)
    private List<EnergyConsumption> energyConsumptions;
    @Column
    private Double co2Emitted;

    @Column
    private Integer greenScore;

    public static EnergyConsumptionData of(List<project.carbonFootprint.models.dto.carbonFootprintData.EnergyConsumptionData> energyConsumptionData){
        EnergyConsumptionData response = EnergyConsumptionData.builder().build();
        List<EnergyConsumption> energyConsumptionList = new ArrayList<>();
        for(project.carbonFootprint.models.dto.carbonFootprintData.EnergyConsumptionData data: energyConsumptionData){
            energyConsumptionList.add(EnergyConsumption.builder()
                            .energyConsumptionData(response)
                            .energyType(data.getEnergyType())
                            .consume(data.getConsume())
                            .timeIntervalInDays(data.getTimeIntervalInDays())
                    .build());
        }
        response.setEnergyConsumptions(energyConsumptionList);
        return response;
    }
}
