package project.activities.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.activities.model.dto.ActivityCreationRequest;
import project.groups.models.Group;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ACTIVITY")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String type;
    private String locationName;
    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY)
    private Set<Invitation> invitations;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    private LocalDateTime creationDate;
    private LocalDateTime celebrationDate;
    private LocalDateTime lastTimeToSignUp;
    @Builder.Default
    private boolean started = false;
    @Builder.Default
    private boolean finished = false;
    @Builder.Default
    private boolean isCanceled = false;
    @Builder.Default
    private boolean isPrivate = false;

    public static Activity of(ActivityCreationRequest request){
        return Activity.builder()
                .title(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .locationName(request.getLocationName())
                .celebrationDate(request.getCelebrationDate())
                .lastTimeToSignUp(request.getLastTimeToSignUp())
                .isPrivate(request.getIsPrivate())
                .build();
    }

}
