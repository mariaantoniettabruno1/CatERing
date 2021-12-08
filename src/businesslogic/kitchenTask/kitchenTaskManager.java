package businesslogic.kitchenTask;

import businesslogic.UseCaseLogicException;
import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class kitchenTaskManager {

    private summarySheet currentSheet;
    private ArrayList<kitchenTaskReceiver> kitchenTaskRecivers;

    public kitchenTaskManager() {
        kitchenTaskRecivers = new ArrayList<>();
    }

    public summarySheet createSummarySheet() throws UseCaseLogicException {

        return currentSheet;
    }

    //event sender methods

    public void notifyCookingTaskAdded(cookingTask ctsk) {
        for (kitchenTaskReceiver ktr : this.kitchenTaskRecivers) {
            ktr.updateCookingTaskAdded(ctsk);
        }
    }

    ;

    public void notifyCookingTaskDeleted(cookingTask ctsk) {
        for (kitchenTaskReceiver ktr : this.kitchenTaskRecivers) {
            ktr.updateCookingTaskDeleted(ctsk);
        }
    }

    ;

    public void notifyCookingTaskEdited(cookingTask ctsk) {
        for (kitchenTaskReceiver ktr : this.kitchenTaskRecivers) {
            ktr.updateCookingTaskEdited(ctsk);
        }
    }

    ;

    public void notifySummarySheetSorted(summarySheet sheet) {
        for (kitchenTaskReceiver ktr : this.kitchenTaskRecivers) {
            ktr.updatesummarySheetSorted(sheet);
        }
    }

    ;

    public void notifyCookingTaskDone(cookingTask ctsk) {
        for (kitchenTaskReceiver ktr : this.kitchenTaskRecivers) {
            ktr.updateCookingTaskDone(ctsk);
        }
    }

    ;

    //operation methods

    public void addCookingTask(Recipe recipe, ObservableList<Turn> turn, Double preparation_time, Integer quantity, Integer difficulty, Integer portions, Integer importance) throws UseCaseLogicException {
        currentSheet.addCookingTask(recipe, turn, preparation_time, quantity, portions, difficulty, importance);
    }

    public void deleteCookingTask(cookingTask ctsk) {
        currentSheet.deleteCookingTask(ctsk);
    }

    public void editCookingTask(ObservableList<Turn> turn, Double preparation_time, Integer quantity, Integer portions, Integer difficulty, Integer importance, cookingTask ctsk) throws UseCaseLogicException {
        currentSheet.editCookingTask(turn, preparation_time, quantity, portions, difficulty, importance, ctsk);
    }

    public void orderSummarySheet(boolean sortedByImportance, boolean sortedByDifficulty) throws UseCaseLogicException {
        currentSheet.orderSummarySheet(sortedByImportance, sortedByDifficulty);
    }

    public summarySheet openSummarySheet() {
        return this.currentSheet;
    }
    public void setCurrentSheet(summarySheet currentSheet){
        this.currentSheet = currentSheet;
    }

    public void makeCookingTaskDone(cookingTask ctsk) {
        currentSheet.makeCookingTaskDone(ctsk);
    }

    public void addReceiver(kitchenTaskReceiver ktr) {
        this.kitchenTaskRecivers.add(ktr);
    }

    public void deleteReceiver(kitchenTaskReceiver ktr) {
        this.kitchenTaskRecivers.remove(ktr);
    }
}
