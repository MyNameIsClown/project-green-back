package project.groups.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.users.models.User;

import java.time.LocalDateTime;


@Entity
@Table(name = "MEMBERSHIP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Membership {
    @EmbeddedId
    private MembershipKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @Column
    private LocalDateTime creationDate;

    @Column(name = "group_role")
    private String groupRole;
}
