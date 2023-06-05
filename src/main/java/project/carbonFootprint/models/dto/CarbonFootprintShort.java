package project.carbonFootprint.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.carbonFootprint.models.CarbonFootprintData;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonFootprintShort {
    Long id;
    Double co2Emitted;
    Timestamp date;
    Integer greenScore;

    public static CarbonFootprintShort of(CarbonFootprintData carbonFootprintData){
        return CarbonFootprintShort.builder()
                .id(carbonFootprintData.getId())
                .co2Emitted(carbonFootprintData.getCo2Emitted())
                .date(carbonFootprintData.getDate())
                .greenScore(carbonFootprintData.getGreenScore())
                .build();
    }
}
