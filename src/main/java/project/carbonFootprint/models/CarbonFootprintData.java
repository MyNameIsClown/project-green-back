package project.carbonFootprint.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.energy.EnergyConsumptionData;
import project.carbonFootprint.models.food.FoodConsumptionData;
import project.carbonFootprint.models.transportation.TransportationUseData;
import project.carbonFootprint.models.waste.WasteProductionData;
import project.users.models.User;

import java.sql.Timestamp;

@Entity
@Table(name="CARBON_FOOTPRINT_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class CarbonFootprintData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;
    @OneToOne(mappedBy = "carbonFootprintData", cascade = CascadeType.ALL)
    private TransportationUseData transportationUseData;
    @OneToOne(mappedBy = "carbonFootprintData", cascade = CascadeType.ALL)
    private EnergyConsumptionData energyConsumptionData;
    @OneToOne(mappedBy = "carbonFootprintData", cascade = CascadeType.ALL)
    private FoodConsumptionData foodConsumptionData;
    @OneToOne(mappedBy = "carbonFootprintData", cascade = CascadeType.ALL)
    private WasteProductionData wasteProductionData;
    @Column
    private Integer co2Emitted;
    @Column
    private Integer greenScore;
}
