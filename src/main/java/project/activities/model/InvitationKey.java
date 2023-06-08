package project.activities.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Embeddable

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class InvitationKey {
    @Column(name = "activity_id")
    private Long activityId;
    @Column(name = "user_id")
    private UUID userId;
}
