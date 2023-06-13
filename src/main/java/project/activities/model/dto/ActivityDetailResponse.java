package project.activities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.activities.model.Activity;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDetailResponse {
    private Long id;
    private String title, description, type, locationName;
    private List<InvitationShortResponse> invitations;
    private LocalDateTime creationDate;
    private LocalDateTime celebrationDate;
    private LocalDateTime lastTimeToSignUp;
    private boolean started = false;
    private boolean finished = false;
    private boolean isCanceled = false;
    private boolean isPrivate = false;

    public static ActivityDetailResponse of(Activity activity){
        return ActivityDetailResponse.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .description(activity.getDescription())
                .type(activity.getType())
                .locationName(activity.getLocationName())
                .creationDate(activity.getCreationDate())
                .celebrationDate(activity.getCelebrationDate())
                .lastTimeToSignUp(activity.getLastTimeToSignUp())
                .started(activity.isStarted())
                .finished(activity.isFinished())
                .isCanceled(activity.isCanceled())
                .isPrivate(activity.isPrivate())
                .build();
    }
}
