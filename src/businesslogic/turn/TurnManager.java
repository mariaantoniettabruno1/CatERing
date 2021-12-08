package businesslogic.turn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TurnManager {
    public TurnManager() {
        TurnBoard.loadAllTurns();
    }

    public ObservableList<Turn> getTurns() {
        return FXCollections.unmodifiableObservableList(TurnBoard.getAllTurns());
    }
}
