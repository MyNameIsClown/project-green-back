package project.carbonFootprint.models.transportation;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.CarbonFootprintData;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TRANSPORTATION_USE_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "carbonFootprintData")
@EntityListeners(AuditingEntityListener.class)
@Log
public class TransportationUseData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carbon_footprint_id", nullable = false, referencedColumnName = "id")
    private CarbonFootprintData carbonFootprintData;
    @OneToMany(mappedBy = "transportationUseData", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TransportationUse> transportationUses;
    @Column
    private Double co2Emitted;
    @Column
    private Integer greenScore;

    public static TransportationUseData of(List<project.carbonFootprint.models.dto.carbonFootprintData.TransportationUseData> transportationUseData){
        TransportationUseData response = TransportationUseData.builder().build();
        List<TransportationUse> transportationUsesList = new ArrayList<>();
        for(project.carbonFootprint.models.dto.carbonFootprintData.TransportationUseData t: transportationUseData){
            transportationUsesList.add(TransportationUse.builder()
                    .vehicleType(t.getVehicleType())
                    .transportationName(t.getTransportName())
                    .distanceTravelInKm(t.getDistanceTravelInKm())
                            .transportationUseData(response)
                    .build());
        }
        response.setTransportationUses(transportationUsesList);
        return response;
    }
}
