package businesslogic.turn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TurnBoard {
    private static Map<Integer, Turn> listofTurns = new HashMap<>();
    //static method for persistence
    public static ObservableList<Turn> loadAllTurns() {
        String query = "SELECT * FROM Turn";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (listofTurns.containsKey(id)) {
                    Turn turn = listofTurns.get(id);
                    turn.duration = rs.getDouble("duration");
                    turn.time = rs.getTime("time");

                } else {
                    Turn turn = new Turn(rs.getDouble("duration"),rs.getTime("time"));
                    turn.id = id;
                    listofTurns.put(turn.id, turn);
                }
            }
        });
        ObservableList<Turn> turnList = FXCollections.observableArrayList(listofTurns.values());
        turnList.sort(Comparator.comparing(Turn::getDuration).thenComparing(Turn::getTime));
        return turnList;
    }

    public static ObservableList<Turn> getAllTurns() {
        return FXCollections.observableArrayList(listofTurns.values());
    }

    public static Turn loadTurnById(int id) {
        if (listofTurns.containsKey(id)) return listofTurns.get(id);
        Turn turn = new Turn();

        String query = "SELECT * FROM Turn WHERE id = " + id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                turn.duration = rs.getDouble("duration");
                turn.time = rs.getTime("time");
                turn.id = id;
                listofTurns.put(turn.id, turn);
            }
        });

        return turn;
    }
}
