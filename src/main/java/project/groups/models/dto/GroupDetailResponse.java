package project.groups.models.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import project.groups.models.Group;
import project.groups.models.Membership;

import java.util.List;
import java.util.stream.Collectors;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GroupDetailResponse extends CreateGroupRequest{
    List<MemberResponse> members;

    public static GroupDetailResponse of(Group group, List<Membership> members){
        return GroupDetailResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .locationName(group.getLocationName())
                .members(members.stream().map((member)->
                    MemberResponse.of(member)
                ).collect(Collectors.toList()))
                .build();
    }
}
