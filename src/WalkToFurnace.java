import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class WalkToFurnace extends Task {

    /**
     * If Inventory contains Ore and Furnace is not reachable, walk to Furnace
     */
    @Override
    public boolean validate() {

        return SceneObjects.getNearest(nearest -> nearest.getName().equals("Furnace") && nearest.isPositionInteractable()) == null
                && Players.getLocal().getTarget() == null
                && Inventory.isFull()
                && ((Inventory.contains("Tin ore") && Inventory.contains("Copper ore")) || Inventory.contains("Iron ore"));
    }

    /**
     * Walk to Furnace
     */
    @Override
    public int execute() {
        Log.info("walk to furnace");
        Positionable position = new Position(3226, 3253, 0);
        Movement.walkTo(position);

        Time.sleepUntil(() -> Players.getLocal().getPosition() != position, Random.mid(10000, 20000));

        return 0;
    }
}
