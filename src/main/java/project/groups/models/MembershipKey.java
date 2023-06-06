package project.groups.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MembershipKey implements Serializable {
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "user_id")
    private UUID userId;
}
