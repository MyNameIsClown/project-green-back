package project.carbonFootprint.models.energy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ENERGY_CONSUMPTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class EnergyConsumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "energy_consumption_id", nullable = false)
    private EnergyConsumptionData energyConsumptionData;
    @Column
    private String energyType;
    @Column
    private Integer consume;
}
