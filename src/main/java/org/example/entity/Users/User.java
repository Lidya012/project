package org.example.entity.Users;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private static User currentUser;
    private Role role;
    private String name;
    private String login;
    private int id;

    private static List<User> users = new ArrayList<>();

    public User(String name, String login, Role role) {

        this.name = name;
        this.login = login;
        this.role = role;
        users.add(this);
    }

    public static void setUsers(List<User> users) {
        User.users.clear();
        User.users.addAll(users);
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public Role getRole() {
        return role;
    }


    public static List<User> getUsers() {
        return users;
    }

    public String getLogin() {
        return login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static User findUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

}
