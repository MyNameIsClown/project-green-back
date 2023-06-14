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
    String name, state;
    LocalDateTime celebrationDate;

    public static ActivityShortResponse of(Activity activity){
        return ActivityShortResponse.builder()
                .id(activity.getId())
                .name(activity.getTitle())
                .state(getStringStateFromBooleans(activity))
                .celebrationDate(activity.getCelebrationDate())
                .build();
    }
    private static String getStringStateFromBooleans(Activity activity){
        String literal = "Waiting";
        if(activity.isStarted()){
            literal = "Started";
        }else if(activity.isCanceled()){
            literal = "Canceled";
        } else if (activity.isFinished()) {
            literal = "Finished";
        }
        return literal;
    }
}
