package project.carbonFootprint.models.waste;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "WASTE_PRODUCTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class WasteProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "waste_production_id", nullable = false)
    private WasteProductionData wasteProductionData;
    @Column
    private String wasteType;
    @Column
    private Integer consume;
}
