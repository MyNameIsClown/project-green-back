package project.carbonFootprint.models.dto.detail;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.carbonFootprint.models.dto.carbonFootprintData.TransportationUseData;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Log
public class TransportationData {
    private Long id;
    private List<TransportationUseData> transportationUseData;
    private Double co2Emitted;
    private Integer greenScore;

    public static TransportationData of (project.carbonFootprint.models.transportation.TransportationUseData transportationUse){
        return TransportationData.builder()
                .id(transportationUse.getId())
                .transportationUseData(transportationUse.getTransportationUses().stream().map(
                        (value) -> TransportationUseData.builder()
                                .transportName(value.getTransportationName())
                                        .vehicleType(value.getVehicleType())
                                                .distanceTravelInKm(value.getDistanceTravelInKm()).build()
                ).collect(Collectors.toList()))
                .co2Emitted(transportationUse.getCo2Emitted())
                .greenScore(transportationUse.getGreenScore())
                .build();
    }
}
