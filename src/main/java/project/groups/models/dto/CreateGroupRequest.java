package project.groups.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import project.groups.models.Group;

@Data
@SuperBuilder
@NoArgsConstructor
public class CreateGroupRequest {
    Long id;
    String name, description, locationName;

    public static CreateGroupRequest of(Group group){
        return CreateGroupRequest.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .locationName(group.getLocationName())
                .build();
    }
}
