package businesslogic.kitchenTask;

import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;
import businesslogic.turn.TurnBoard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class cookingTask {
    private int id;
    private Recipe recipe;
    private ObservableList<Turn> turn;
    private Double preparation_time;
    private Integer quantity;
    private Integer difficulty;
    private Integer portions;
    private Integer importance;
    private boolean completed = false;

    public cookingTask(Recipe recipe, ObservableList<Turn> turn) {
        this.recipe = recipe;
        this.turn = turn;
    }

    public cookingTask() {

    }

    public cookingTask create(Recipe recipe, ObservableList<Turn> turn, Double preparation_time, Integer quantity, Integer difficulty, Integer portions, Integer importance) {
        this.recipe = recipe;
        this.turn = turn;
        this.preparation_time = preparation_time;

        if (quantity == null || quantity < 1) this.quantity = -1;
        else this.quantity = quantity;
        if (difficulty == null || difficulty < 1 || difficulty > 5) this.difficulty = 0;
        else this.difficulty = difficulty;
        if (portions == null || portions < 1) this.portions = -1;
        else this.portions = portions;
        if (importance == null || importance < 1 || importance > 10) this.importance = 0;
        else this.importance = importance;

        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(double preparation_time) {
        this.preparation_time = preparation_time;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if (quantity != null && quantity > 0) this.quantity = quantity;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        if (difficulty != null) this.difficulty = difficulty;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        if (difficulty != null && portions > 0) this.portions = portions;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        if (importance != null) this.importance = importance;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public static class ImportanceComparator implements Comparator<cookingTask> {
        @Override
        public int compare(cookingTask ctsk1, cookingTask ctsk2) {
            return (ctsk1.importance.compareTo(ctsk2.importance));
        }
    }

    public static class DifficultyComparator implements Comparator<cookingTask> {
        @Override
        public int compare(cookingTask ctsk1, cookingTask ctsk2) {
            return (ctsk1.difficulty.compareTo(ctsk2.difficulty));
        }
    }

    public static ObservableList<cookingTask> loadCookingTaskForId(int id) {
        final ObservableList<cookingTask> result = FXCollections.observableArrayList();
        String query = "SELECT * FROM cookingtask WHERE id = " + id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
                    @Override
                    public void handle(ResultSet rs) throws SQLException {
                        cookingTask ctsk = new cookingTask(Recipe.loadRecipeById(rs.getInt("recipe")), TurnBoard.loadTurnOfCoookingTaskById(id));
                        ctsk.id = rs.getInt("id");
                        ctsk.quantity = rs.getInt("quantity");
                        ctsk.completed = rs.getBoolean("completed");
                        ctsk.importance = rs.getInt("importance");
                        ctsk.difficulty = rs.getInt("difficulty");
                        ctsk.preparation_time = rs.getDouble("preparation_time");
                        ctsk.portions = rs.getInt("portions");
                        result.add(ctsk);
                    }
                }

        );

        return result;
    }
    public static ObservableList<cookingTask> loadAllCookingTasks(){
        ObservableList<cookingTask> result = FXCollections.observableArrayList();
        String query = "SELECT * FROM cookingTask";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                cookingTask ctsk = new cookingTask();
                ctsk.recipe = Recipe.loadRecipeById(rs.getInt("id_recipe"));
                ctsk.id = rs.getInt("id");
                ctsk.quantity = rs.getInt("quantity");
                ctsk.completed = rs.getBoolean("completed");
                ctsk.importance = rs.getInt("importance");
                ctsk.difficulty = rs.getInt("difficulty");
                ctsk.preparation_time = rs.getDouble("preparation_time");
                ctsk.portions = rs.getInt("portions");
                ctsk.turn = TurnBoard.getAllTurns();
                result.add(ctsk);
            }
        });
        return result;
    }
    public static void createNewCookingTask(cookingTask ctsk) {
        String itemInsert = "INSERT INTO cookingtask (id_recipe,preparation_time,quantity,difficulty,portions,importance) VALUES (" + ctsk.recipe.getId() + ", " + ctsk.preparation_time + ", " + ctsk.quantity + "," + ctsk.difficulty + "," + ctsk.portions + "," + ctsk.importance + ");";
        PersistenceManager.executeUpdate(itemInsert);
        ctsk.id = PersistenceManager.getLastId();
        for (Turn turn : ctsk.turn) {
            String itemTurnInsert = "INSERT INTO cookingtask_has_turn (id_cookingtask, id_turn) VALUES (" + ctsk.id + "," + turn.getId() + ");";
            PersistenceManager.executeUpdate(itemTurnInsert);
        }

    }

    public static void editCookingTask(cookingTask ctsk) {
        String itemEdit = "UPDATE  cookingtask SET preparation_time="+ctsk.preparation_time+",quantity="+ctsk.quantity+",difficulty="+ctsk.difficulty+",portions="+ctsk.portions+",importance="+ctsk.importance+" WHERE id="+ctsk.id+";";
        PersistenceManager.executeUpdate(itemEdit);
    }

    public void makeCookingTaskDoneDB(cookingTask ctsk) {
        String completedItem = "UPDATE  cookingtask SET completed=true WHERE id="+ctsk.id+";";
        PersistenceManager.executeUpdate(completedItem);


    }

    public void deleteCookingTaskDB(cookingTask ctsk) {
        String remove = "DELETE FROM cookingtask WHERE id =" + ctsk.id+";";
        PersistenceManager.executeUpdate(remove);
    }

    public java.lang.String toString() {
        return "cookingTask{" +
                " recipe=" + recipe +
                ", turns=" + turn +
                ", quantity=" + quantity +
                ", time=" + preparation_time +
                ", portions=" + portions +
                ", difficulty=" + difficulty +
                ", importance=" + importance +
                '}';
    }
}
