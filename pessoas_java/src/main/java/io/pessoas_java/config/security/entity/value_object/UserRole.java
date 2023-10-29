package io.pessoas_java.config.security.entity.value_object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {

    ADMIN("Admin"),
    USER("User");

    private final String role;
}

