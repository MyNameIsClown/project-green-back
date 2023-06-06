package project.groups.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.groups.models.Group;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGroupRequest {
    String name, description, locationName;

    public static CreateGroupRequest of(Group group){
        return CreateGroupRequest.builder()
                .name(group.getName())
                .description(group.getDescription())
                .locationName(group.getLocationName())
                .build();
    }
}
