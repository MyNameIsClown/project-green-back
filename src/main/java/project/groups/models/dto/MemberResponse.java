package project.groups.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.groups.models.Membership;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
    String username, groupRole;

    public static MemberResponse of(Membership membership){
        return MemberResponse.builder()
                .username(membership.getUser().getUsername())
                .groupRole(membership.getGroupRole())
                .build();
    }
}
