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
public class ActivityDetailBasicResponse {
    private Long id;
    private String title, description, type, locationName;
    private LocalDateTime celebrationDate;
    private LocalDateTime lastTimeToSignUp;
    private boolean started = false;
    private boolean hasJoined;
    private boolean hasSuscribeToGroup;

    public static ActivityDetailBasicResponse of(Activity activity){
        return ActivityDetailBasicResponse.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .description(activity.getDescription())
                .type(activity.getType())
                .locationName(activity.getLocationName())
                .celebrationDate(activity.getCelebrationDate())
                .lastTimeToSignUp(activity.getLastTimeToSignUp())
                .started(activity.isStarted())
                .build();
    }
}
