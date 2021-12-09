package businesslogic;

import businesslogic.event.EventManager;
import businesslogic.kitchenTask.kitchenTaskManager;
import businesslogic.menu.MenuManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.turn.TurnManager;
import businesslogic.user.UserManager;
import persistence.MenuPersistence;


public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private MenuManager menuMgr;
    private RecipeManager recipeMgr;
    private UserManager userMgr;
    private EventManager eventMgr;
    private kitchenTaskManager kitchenTaskMgr;
    private TurnManager turnMgr;
    private MenuPersistence menuPersistence;

    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
        menuPersistence = new MenuPersistence();
        kitchenTaskMgr = new kitchenTaskManager();
        turnMgr = new TurnManager();
        menuMgr.addEventReceiver(menuPersistence);
    }


    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public EventManager getEventManager() { return eventMgr; }

    public  kitchenTaskManager getKitchenTaskManager(){ return kitchenTaskMgr;}

    public TurnManager getTurnMgr() {
        return turnMgr;
    }
}
