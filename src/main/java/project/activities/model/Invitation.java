package project.activities.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.users.models.User;
@Entity
@Table(name = "INVITATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invitation {
    @EmbeddedId
    private InvitationKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("activityId")
    @JoinColumn(name = "activity_id")
    private Activity activity;
    @Builder.Default
    private boolean isUserPresent = false;

}
