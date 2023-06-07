package project.carbonFootprint.models.food;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.CarbonFootprintData;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FOOD_CONSUMPTION_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "carbonFootprintData")
@EntityListeners(AuditingEntityListener.class)
@Log
public class FoodConsumptionData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carbon_footprint_id", nullable = false, referencedColumnName = "id")
    private CarbonFootprintData carbonFootprintData;
    @OneToMany(mappedBy = "foodConsumptionData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FoodConsumption> foodConsumptions;
    @Column
    private Integer co2Emitted;
    @Column
    private Integer greenScore;

    public static FoodConsumptionData of(List<project.carbonFootprint.models.dto.carbonFootprintData.FoodConsumptionData> foodConsumptionData){
        FoodConsumptionData response = FoodConsumptionData.builder().build();
        List<FoodConsumption> foodConsumptionList = new ArrayList<>();
        for(project.carbonFootprint.models.dto.carbonFootprintData.FoodConsumptionData data: foodConsumptionData){
            foodConsumptionList.add(FoodConsumption.builder()
                            .foodConsumptionData(response)
                            .foodType(data.getFoodType())
                            .consume(data.getConsume())
                    .build());
        }
        response.setFoodConsumptions(foodConsumptionList);
        return response;
    }
}
