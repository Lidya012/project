package org.example.entity;

import java.io.Serializable;

public enum TypeOfGame implements Serializable {
    CHESS("Шахматы"), CHECKERS("Шашки"), BACKGAMMON("Нарды");
    String name;

    TypeOfGame(String name) {
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
