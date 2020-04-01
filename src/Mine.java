import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Mine extends Task {

    /**
     * If Rocks are reachable and Player has Pickaxe and free Inventory slots, start mining
     */
    @Override
    public boolean validate() {
        return SceneObjects.getNearest(nearest -> nearest.getName().equals("Rocks") && nearest.isPositionInteractable()) != null
                && Players.getLocal().getTarget() == null
                && !Inventory.isFull()
                && Inventory.contains("Bronze pickaxe");
    }

    /**
     * Mine reachable Rocks with Pickaxe
     */
    @Override
    public int execute() {
        Log.info("mine");

        SceneObject rocks = SceneObjects.getNearest(nearest -> nearest.getName().equals("Rocks") && nearest.isPositionInteractable());
        final int remainingSlots = Inventory.getFreeSlots();

        if(rocks != null) {
            rocks.interact("Mine");
            Time.sleepUntil(() -> Inventory.getFreeSlots() != remainingSlots, Random.mid(1000, 2000));
        }

        return Random.mid(500, 1000);
    }
}
