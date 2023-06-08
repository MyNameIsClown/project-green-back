package project.activities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityCreationRequest {
    String name, description, type, locationName;
    LocalDateTime celebrationDate, lastTimeToSignUp;
    Long groupId;
    Boolean isPrivate;
}
