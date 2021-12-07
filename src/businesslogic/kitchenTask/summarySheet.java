package businesslogic.kitchenTask;

import businesslogic.UseCaseLogicException;
import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class summarySheet extends cookingTask {
    private final ArrayList<cookingTask> sheetCookingTask;

    public summarySheet(ArrayList<cookingTask> sheetCookingTask) {
        this.sheetCookingTask = sheetCookingTask;
    }

    public summarySheet() {
        this.sheetCookingTask = new ArrayList<cookingTask>();
    }

    public void addCookingTask(Recipe recipe, ArrayList<Turn> turn, double preparation_time, Integer quantity, Integer difficulty, Integer portions, Integer importance) throws UseCaseLogicException {
        if(recipe!=null && preparation_time!=0.00){
            cookingTask ctsk = create(recipe, turn, preparation_time, quantity, difficulty, portions, importance);
            sheetCookingTask.add(ctsk);
        }
        else throw new UseCaseLogicException();

    }

    public void deleteCookingTask(cookingTask ctsk) {
        sheetCookingTask.remove(ctsk);
    }

    public void editCookingTask(ArrayList<Turn> turn, double preparation_time, Integer quantity, Integer portions, Integer difficulty, Integer importance, cookingTask ctsk)throws UseCaseLogicException {
        if (preparation_time!=0.00) {
            ctsk.setDifficulty(difficulty);
            ctsk.setImportance(importance);
            ctsk.setPortions(portions);
            ctsk.setQuantity(quantity);
            ctsk.setPreparation_time(preparation_time);
        }
        else throw new UseCaseLogicException();
    }

    public void orderSummarySheet(boolean sortedByImportance, boolean sortedByDifficulty) throws UseCaseLogicException {
        if (sortedByDifficulty == sortedByImportance) throw new UseCaseLogicException();

        else if (sortedByDifficulty) {
            sheetCookingTask.sort(new DifficultyComparator());
        } else {
            sheetCookingTask.sort(new ImportanceComparator());
        }

    }
public ArrayList<cookingTask> openSummarySheet(){
        return this.sheetCookingTask;
}
public void makeCookingTaskDone(cookingTask ctsk){
        ctsk.setCompleted(true);
}
}