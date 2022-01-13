import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.kitchenTask.cookingTask;
import businesslogic.kitchenTask.kitchenTaskManager;
import businesslogic.kitchenTask.summarySheet;
import businesslogic.menu.Menu;
import businesslogic.recipe.Recipe;
import businesslogic.turn.Turn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TestKitchenTask {

    public static void main(String[] args) {
        try {
            System.out.println("Test fake login");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nTest load menu");
            ObservableList<Menu> menuList = CatERing.getInstance().getMenuManager().getAllMenus();
            System.out.println(menuList.get(0).testString());

            System.out.println("\n Test for open current summarySheet");
            kitchenTaskManager ctaskmng = CatERing.getInstance().getKitchenTaskManager();
            System.out.println(ctaskmng.openSummarySheet().getSheetCookingTask());

            System.out.println("\n Test for create cooking task");
            ObservableList<Recipe> recipeList = CatERing.getInstance().getRecipeManager().getRecipes();
            ObservableList<Turn> turnList = CatERing.getInstance().getTurnMgr().getTurns();
            ObservableList<Turn> selectedTurn = FXCollections.observableArrayList();
            selectedTurn.add(turnList.get(2));
            selectedTurn.add(turnList.get(3));
            //CatERing.getInstance().getKitchenTaskManager().addCookingTask(recipeList.get(4), selectedTurn, null, null, null, null, null);
            CatERing.getInstance().getKitchenTaskManager().addCookingTask(recipeList.get(5), selectedTurn, 3.0, null, null, null, null);
            CatERing.getInstance().getKitchenTaskManager().addCookingTask(recipeList.get(6), selectedTurn, 2.0, 1, 2, 3, 4);



            System.out.println("\n Specify quantity, portions, difficulty and importance ");
            ObservableList<cookingTask> cookingTaskList = ctaskmng.openSummarySheet().getSheetCookingTask();
            CatERing.getInstance().getKitchenTaskManager().editCookingTask(null, null, 3, 5, 1, 2, cookingTaskList.get(1));
            // CatERing.getInstance().getKitchenTaskManager().editCookingTask(null, null,2,4,2,1,cookingTaskList.get(2));
            // CatERing.getInstance().getKitchenTaskManager().editCookingTask(null, null,1,3,4,5,cookingTaskList.get(3));

            System.out.println("\n Test for order summarySheet by Difficulty");
            boolean difficulty = true;
            boolean importance = false;
            CatERing.getInstance().getKitchenTaskManager().orderSummarySheet(difficulty, importance);
            System.out.println("\n summarySheet is ordered by difficulty.");

            System.out.println("\n Test for order summarySheet by Importance.");
            difficulty = false;
            importance = true;
            CatERing.getInstance().getKitchenTaskManager().orderSummarySheet(difficulty, importance);
            System.out.println("\n summarySheet is ordered by importance.");

            System.out.println("\n Test for check cooking task completed.");
            CatERing.getInstance().getKitchenTaskManager().makeCookingTaskDone(cookingTaskList.get(0));
            System.out.println("\n Cooking task is marked as done.");

            System.out.println("\n Test for delete cooking task.");
            CatERing.getInstance().getKitchenTaskManager().deleteCookingTask(cookingTaskList.get(1));
            System.out.println("\n Cooking task is deleted.");

        } catch (UseCaseLogicException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Non hai inserito o Ricetta, o preparation time oppure non hai associato il turno al compito\n");
            System.out.println("Riprova!");
            e.printStackTrace();
        }
    }
}
