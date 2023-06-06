package project.groups.models;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum GroupRoleTypes {
    MEMBER("member"), OWNER("owner");

    @Getter
    private String value;
}
