package project.carbonFootprint.models.dto.carbonFootprintData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodConsumptionData {
    String foodType;
    Integer consume;
}
