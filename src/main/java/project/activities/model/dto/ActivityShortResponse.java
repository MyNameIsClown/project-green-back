package project.activities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.activities.model.Activity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityShortResponse {
    Long id;
    String name, type, locationName;
    LocalDateTime celebrationDate;

    public static ActivityShortResponse of(Activity activity){
        return ActivityShortResponse.builder()
                .id(activity.getId())
                .name(activity.getTitle())
                .type(activity.getType())
                .locationName(activity.getLocationName())
                .celebrationDate(activity.getCelebrationDate())
                .build();
    }
}
