package org.example.entity.Users;

import java.io.Serializable;

public enum Role implements Serializable {
    ADMIN("Администратор"), PL("Игрок");
    String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

