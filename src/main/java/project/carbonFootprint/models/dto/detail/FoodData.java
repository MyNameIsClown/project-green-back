package project.carbonFootprint.models.dto.detail;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.dto.carbonFootprintData.FoodConsumptionData;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class FoodData {
    private Long id;
    private List<FoodConsumptionData> foodConsumptionData;
    private Double co2Emitted;
    private Integer greenScore;

    public static FoodData of (project.carbonFootprint.models.food.FoodConsumptionData foodData){
        return FoodData.builder()
                .id(foodData.getId())
                .foodConsumptionData(foodData.getFoodConsumptions().stream().map(
                        (value) -> FoodConsumptionData.builder()
                                .foodType(value.getFoodType())
                                .consume(value.getConsume())
                                .build()
                ).collect(Collectors.toList()))
                .co2Emitted(foodData.getCo2Emitted())
                .greenScore(foodData.getGreenScore())
                .build();
    }
}
