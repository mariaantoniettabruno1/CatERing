package businesslogic.kitchenTask;

public interface kitchenTaskReceiver {
    public  void updateCookingTaskAdded(cookingTask ctsk);
    public  void updateCookingTaskDeleted(cookingTask ctsk);
    public  void updateCookingTaskEdited(cookingTask ctsk);
    public void updatesummarySheetSorted(summarySheet sheet);
    public void updateCookingTaskDone(cookingTask ctsk);
}

