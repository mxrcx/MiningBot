import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Smith extends Task {

    /**
     * If Inventory contains Metal Bars and Anvil is reachable, start smithing
     */
    @Override
    public boolean validate() {

        return SceneObjects.getNearest(nearest -> nearest.getName().equals("Anvil") && nearest.isPositionInteractable()) != null
                && Players.getLocal().getTarget() == null
                && Inventory.contains("Bronze bar");
    }

    /**
     * Smith Metal Bars
     */
    @Override
    public int execute() {
        Log.info("smith");

        SceneObject anvil = SceneObjects.getNearest(nearest -> nearest.getName().equals("Anvil") && nearest.isPositionInteractable());
        final int remainingBars = Inventory.getCount("Bronze bar");

        if(anvil != null) {
            anvil.interact("Smith");
            Time.sleepUntil(() -> Inventory.getCount("Bronze bar") != remainingBars, Random.mid(1000, 2000));
        }

        return Random.mid(500, 1000);
    }
}