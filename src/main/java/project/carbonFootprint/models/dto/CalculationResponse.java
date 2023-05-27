package project.carbonFootprint.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.users.models.dto.UserResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalculationResponse {
    UserResponse user;
    Double totalCo2Emitted;
    Integer totalGreenScore;
}
