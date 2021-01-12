package com.sasha.grodno.website.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER ("ROLE_USER");

    private String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}

