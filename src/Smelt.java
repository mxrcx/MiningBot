import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Smelt extends Task {

    /**
     * If Inventory contains Ore and Furnace is reachable, start smelting
     */
    @Override
    public boolean validate() {

        return SceneObjects.getNearest(nearest -> nearest.getName().equals("Furnace") && nearest.isPositionInteractable()) != null
                && Players.getLocal().getTarget() == null
                && Inventory.contains("Tin ore")
                && Inventory.contains("Copper ore");
    }

    /**
     * Smelt Ore
     */
    @Override
    public int execute() {
        Log.info("smelt");

        SceneObject furnace = SceneObjects.getNearest(nearest -> nearest.getName().equals("Furnace") && nearest.isPositionInteractable());
        final int availableBars = Inventory.getCount("Bronze bar");

        if(furnace != null) {
            furnace.interact("Smelt");
            Time.sleepUntil(() -> Inventory.getCount("Bronze bar") != availableBars, Random.mid(1000, 2000));
        }

        return Random.mid(500, 1000);
    }
}
