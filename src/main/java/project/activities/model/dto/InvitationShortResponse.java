package project.activities.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.activities.model.Invitation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationShortResponse {
    String username, userRoleOnGroup;
    Boolean isUserPresent;

    public static InvitationShortResponse of(Invitation invitation){
        return InvitationShortResponse.builder()
                .username(invitation.getUser().getUsername())
                .isUserPresent(invitation.isUserPresent())
                .build();

    }
}
