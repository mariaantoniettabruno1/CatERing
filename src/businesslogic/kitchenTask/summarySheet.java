package businesslogic.kitchenTask;

import businesslogic.UseCaseLogicException;
import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class summarySheet extends cookingTask {
    private ObservableList<cookingTask> sheetCookingTask;

    public summarySheet() {
        this.sheetCookingTask = FXCollections.observableArrayList(loadAllCookingTasks());
    }

    public void addCookingTask(Recipe recipe, ObservableList<Turn> turn, double preparation_time, Integer quantity, Integer difficulty, Integer portions, Integer importance) throws UseCaseLogicException {
        if (recipe != null && preparation_time != 0.00) {
            cookingTask ctsk = new cookingTask();
            ctsk = create(recipe, turn, preparation_time, quantity, difficulty, portions, importance);
            sheetCookingTask.add(ctsk);
            createNewCookingTask(ctsk);
        } else throw new UseCaseLogicException();

    }

    public void deleteCookingTask(cookingTask ctsk) {
        sheetCookingTask.remove(ctsk);
        deleteCookingTaskDB(ctsk);
    }

    public void editCookingTask(ObservableList<Turn> turn, Double preparation_time, Integer quantity, Integer portions, Integer difficulty, Integer importance, cookingTask ctsk) throws UseCaseLogicException {
        ctsk.setDifficulty(difficulty);
        ctsk.setImportance(importance);
        ctsk.setPortions(portions);
        ctsk.setQuantity(quantity);
        editCookingTask(ctsk);
    }

    public void orderSummarySheet(boolean sortedByImportance, boolean sortedByDifficulty) throws UseCaseLogicException {
        if (sortedByDifficulty == sortedByImportance) throw new UseCaseLogicException();

        else if (sortedByDifficulty) {
            sheetCookingTask.sort(new DifficultyComparator());
        } else {
            sheetCookingTask.sort(new ImportanceComparator());
        }

    }

    public ObservableList<cookingTask> getSheetCookingTask() {
        return sheetCookingTask;
    }

    public void makeCookingTaskDone(cookingTask ctsk) {
        ctsk.setCompleted(true);
        makeCookingTaskDoneDB(ctsk);
    }

    @Override
    public String toString() {
        return "summarySheet{" +
                "sheetCookingTask=" + sheetCookingTask +
                '}';
    }
}