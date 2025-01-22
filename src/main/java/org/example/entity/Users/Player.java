package org.example.entity.Users;

import org.example.entity.Tournament;
import org.example.entity.TournamentMenager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Player extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    public int age;
    public String surname;

    public Player(String surname, String name, String login, int age) {
        super(name, login, Role.PL);
        this.surname =surname;
        this.age = age;
    }
    public List<Tournament> getRegisteredTournaments() {
        List<Tournament> registeredTournaments = new ArrayList<>();
        List<Tournament> tournaments = TournamentMenager.getInstance().getTournaments();

        for (Tournament tournament : tournaments) {
            if (tournament.getPlayers().contains(this)) {
                registeredTournaments.add(tournament);
            }
        }

        return registeredTournaments;
    }


    @Override
    public String toString() {
        return getName() + " Логин: " + getLogin();
    }
}
