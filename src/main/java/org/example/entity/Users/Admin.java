package org.example.entity.Users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User implements Serializable {

    public Admin(String name, String login) {
        super(name, login, Role.ADMIN);
    }

    @Override
        public String toString() {
            return getName() + " Логин:" + getLogin();
        }

}
