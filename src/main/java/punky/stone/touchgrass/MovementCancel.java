package punky.stone.touchgrass;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import punky.stone.challengeplugin.ChallengeEvents;

public class MovementCancel implements Listener, ChallengeEvents {
    private final TouchGrass plugin;
    private boolean movementDisabled = true;

    public MovementCancel(TouchGrass plugin) {
        this.plugin = plugin;
    }

    public void init() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!movementDisabled)
            return;
        if (event.getFrom().getX() != event.getTo().getX() ||
                event.getFrom().getY() != event.getTo().getY() ||
                event.getFrom().getZ() != event.getTo().getZ()) {
            event.setTo(event.getFrom());
        }
    }

    @Override
    public void onTimerResumed() {
        movementDisabled = false;
    }

    @Override
    public void onTimerPaused() {
        movementDisabled = true;
    }

    @Override
    public void onChallengeRestart() {

    }
}
