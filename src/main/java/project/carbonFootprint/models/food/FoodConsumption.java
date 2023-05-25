package project.carbonFootprint.models.food;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "FOOD_CONSUMPTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class FoodConsumption {
    @Id
    @ManyToOne
    @JoinColumn(name = "food_consumption_id", nullable = false)
    private FoodConsumptionData foodConsumptionData;
    @Column
    private String foodType;
    @Column
    private Integer consume;
}
