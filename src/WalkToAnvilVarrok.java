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

public class WalkToAnvilVarrok extends Task {
    /**
     * If Inventory contains Bronze bars and Furnace is not reachable, walk to Anvil in Varrok
     */
    @Override
    public boolean validate() {

        return SceneObjects.getNearest(nearest -> nearest.getName().equals("Anvil") && nearest.isPositionInteractable()) == null
                && Players.getLocal().getTarget() == null
                && (Inventory.contains("Bronze bar") || Inventory.contains("Iron bar"))
                && (!Inventory.contains("Copper ore") && !Inventory.contains("Copper ore"));
    }

    /**
     * Walk to Anvil in Varrok
     */
    @Override
    public int execute() {
        Log.info("walk to anvil");
        Positionable position = new Position(3229, 3434, 0);
        Movement.walkTo(position);

        Time.sleepUntil(() -> Players.getLocal().getPosition() != position, Random.mid(10000, 20000));

        return 0;
    }
}
