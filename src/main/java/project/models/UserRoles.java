package project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserRoles {
    ADMIN("admin"),
    USER("user");
    private final String roleName;
    public static UserRoles fromString(String text) {
        return Arrays.stream(UserRoles.values())
                .filter(role -> role.roleName.equalsIgnoreCase(text))
                .findFirst()
                .orElse(null);
    }

}
