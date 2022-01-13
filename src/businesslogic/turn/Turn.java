package businesslogic.turn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Turn {

    public int id;
    public double duration;
    public Time time;

    public Turn(double duration, Time time) {
        this.duration = duration;
        this.time = time;
    }

    public Turn() {
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Turn{" +
                "id=" + id +
                ", duration=" + duration +
                ", time=" + time +
                '}';
    }
}
