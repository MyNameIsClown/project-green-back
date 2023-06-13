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
    String name;
    LocalDateTime celebrationDate;
    Boolean privacity;

    public static ActivityShortResponse of(Activity activity){
        return ActivityShortResponse.builder()
                .id(activity.getId())
                .name(activity.getTitle())
                .privacity(activity.isPrivate())
                .celebrationDate(activity.getCelebrationDate())
                .build();
    }
}
