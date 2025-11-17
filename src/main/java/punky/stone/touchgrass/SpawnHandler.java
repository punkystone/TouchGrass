package punky.stone.touchgrass;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import punky.stone.challengeplugin.ChallengePluginAPI;

import java.util.List;

public class SpawnHandler implements Listener {
    private final TouchGrass plugin;
    private final ChallengePluginAPI challengePluginAPI;

    public SpawnHandler(TouchGrass plugin, ChallengePluginAPI challengePluginAPI) {
        this.plugin = plugin;
        this.challengePluginAPI = challengePluginAPI;
    }

    public void init() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (challengePluginAPI.isRunning()) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(plugin, () -> handleSpawn(event.getPlayer()), 1L);
    }


    private void handleSpawn(Player player) {
        Location location = player.getLocation().clone();
        World world = location.getWorld();
        if (world == null) return;
        Location below = location.clone().add(0, -1, 0);
        List<Material> grassBlocks = GrassBlocks.getGrassBlocks(plugin);
        for (Material grassBlock : grassBlocks) {
            if (below.getBlock().getType() == grassBlock) {
                Location newLocation = location.clone().add(0, 1, 0);
                player.teleport(newLocation);
                Location glassBlockLocation = newLocation.clone().add(0, -1, 0);
                glassBlockLocation.getBlock().setType(Material.GLASS);
                break;
            }
        }
    }
}
