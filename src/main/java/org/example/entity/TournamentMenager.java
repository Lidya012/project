package org.example.entity;

import lombok.Data;
import org.example.Util.Logger;
import org.example.Util.UtilInput;
import org.example.bd.WorkerBd;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
public class TournamentMenager {
    private static TournamentMenager INSTANCE;
    private List<Tournament> tournaments;

    private TournamentMenager() {
        tournaments = new ArrayList<>();
    }

    public static TournamentMenager getInstance() {
        if (INSTANCE == null) {
           INSTANCE = new TournamentMenager();
        }
        return INSTANCE;
    }

    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public static Tournament create() {
        Tournament tournament = new Tournament(null, null);
        System.out.println("Выберите тип игры");
        List<TypeOfGame> type = new ArrayList<>();
        type.add(TypeOfGame.CHESS);
        type.add(TypeOfGame.BACKGAMMON);
        type.add(TypeOfGame.CHECKERS);
        for (int i = 0; i < type.size(); i++) {
            System.out.printf("%d - %s%n", i, type.get(i)); // Индексы от 1 до size
        }
            int numberChoose = UtilInput.getRequiredIntFromUser(0, type.size() - 1);
            switch (numberChoose) {
                case 0:
                    tournament.setTypeOfGame(TypeOfGame.CHESS);
                    break;
                case 1:
                    tournament.setTypeOfGame(TypeOfGame.BACKGAMMON);
                    break;
                case 2:
                    tournament.setTypeOfGame(TypeOfGame.CHECKERS);
                    break;
            }

        System.out.println("Назначьте дату ");
        LocalDate date = UtilInput.getRequiredDateFromUser();
        tournament.setData(date);
        TournamentMenager.getInstance().addTournament(tournament);
        WorkerBd.INSTANCE.addTournament(tournament);
        Logger.getInstance().log("Создан новый чемпионат: " + tournament.toString());
        return tournament;
    }

}
