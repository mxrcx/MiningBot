import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(name = "MiningBot", developer = "Marco Schaarschmidt", desc = "This is a MiningBot. It automates the Mining and Smithing process.", category = ScriptCategory.MINING)
public class Main extends TaskScript {

    /**
     * Start a Mine and Smith Task
     */
    @Override
    public void onStart() {
        submit(new Mine(), new Smith());
    }
}
