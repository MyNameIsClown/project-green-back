package project.carbonFootprint.models.transportation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TRANSPORTATION_USE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class TransportationUse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "transportation_use_id")
    private TransportationUseData transportationUseData;
    @Column
    private String transportationName;
    /**
     * Tipo de vehiculo
     */
    @Column
    private String vehicleType;
    /**
     * Cantidad de kilometos anuales
     */
    @Column
    private Integer distanceTravelInKm;
}
