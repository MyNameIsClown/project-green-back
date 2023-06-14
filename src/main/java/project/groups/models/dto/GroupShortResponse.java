package project.groups.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import project.groups.models.Group;

@Data
@SuperBuilder
@NoArgsConstructor
public class GroupShortResponse {
    Long id;
    String name, description, locationName;

    Boolean currentUserIsRegistrated;
    Boolean currentUserIsTheOwner;

    public static GroupShortResponse of(Group group){
        return GroupShortResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .locationName(group.getLocationName())
                .build();
    }
}
