package project.carbonFootprint.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.carbonFootprint.models.dto.carbonFootprintData.EnergyConsumptionData;
import project.carbonFootprint.models.dto.carbonFootprintData.FoodConsumptionData;
import project.carbonFootprint.models.dto.carbonFootprintData.TransportationUseData;
import project.carbonFootprint.models.dto.carbonFootprintData.WasteProductionData;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculationRequest {
    List<TransportationUseData> transportationUseData;
    List<EnergyConsumptionData> energyConsumptionData;
    List<FoodConsumptionData> foodConsumptionData;
    List<WasteProductionData> wasteProductionData;
}
