package project.carbonFootprint.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.carbonFootprint.models.CarbonFootprintData;
import project.carbonFootprint.models.dto.detail.EnergyData;
import project.carbonFootprint.models.dto.detail.FoodData;
import project.carbonFootprint.models.dto.detail.TransportationData;
import project.carbonFootprint.models.dto.detail.WasteData;

import java.sql.Timestamp;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonFootprintDetail {
    Long id;
    Integer co2Emitted;
    Timestamp date;
    Integer greenScore;
    TransportationData transportationData;
    EnergyData energyData;
    FoodData foodData;
    WasteData wasteData;

    public static CarbonFootprintDetail of(CarbonFootprintData carbonFootprintData){
        return CarbonFootprintDetail.builder()
                .id(carbonFootprintData.getId())
                .co2Emitted(carbonFootprintData.getCo2Emitted())
                .date(carbonFootprintData.getDate())
                .greenScore(carbonFootprintData.getGreenScore())
                .energyData(EnergyData.of(carbonFootprintData.getEnergyConsumptionData()))
                .transportationData(TransportationData.of(carbonFootprintData.getTransportationUseData()))
                .foodData(FoodData.of(carbonFootprintData.getFoodConsumptionData()))
                .wasteData(WasteData.of(carbonFootprintData.getWasteProductionData()))
                .build();
    }
}
