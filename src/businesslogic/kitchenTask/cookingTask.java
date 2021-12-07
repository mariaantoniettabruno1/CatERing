package businesslogic.kitchenTask;

import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;

import java.util.ArrayList;
import java.util.Comparator;

public class cookingTask {
    private Recipe recipe;
    private ArrayList<Turn> turn;
    private double preparation_time;
    private Integer quantity;
    private Integer difficulty;
    private Integer portions;
    private Integer importance;
    private boolean completed = false;

    public cookingTask create(Recipe recipe, ArrayList<Turn> turn, double preparation_time, Integer quantity, Integer difficulty, Integer portions, Integer importance) {
        this.recipe = recipe;
        this.turn = turn;
        this.preparation_time = preparation_time;

        if (quantity == null || quantity < 1) this.quantity = -1;
        if (difficulty == null || difficulty < 1 || difficulty>5) this.difficulty = 0;
        if (portions == null || portions < 1) this.portions = -1;
        if (importance == null || importance < 1|| importance>10) this.importance = 0;

        return this;
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
        this.quantity = quantity;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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
