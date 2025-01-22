package org.example.entity;

import lombok.Data;

import org.example.entity.Users.Player;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
public class Tournament implements Serializable {
    public LocalDate data;
    public TypeOfGame typeOfGame;
    private List<Player>players;
private int id;

    public Tournament(LocalDate date, TypeOfGame typeOfGame) {
        this.data = date;
        this.typeOfGame = typeOfGame;
        this.players=new ArrayList<>();

    }
    public void addPlayer(Player player) {
          players.add(player);

    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "data=" + data +
                ", typeOfGame=" + typeOfGame +
                ", players=" + players +
                '}';
    }
}

