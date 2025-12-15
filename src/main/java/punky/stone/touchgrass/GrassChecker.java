package punky.stone.touchgrass;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import punky.stone.challengeplugin.ChallengePluginAPI;

import java.util.List;

public class GrassChecker {
    private final TouchGrass plugin;
    private final ChallengePluginAPI challengePluginAPI;
    private final List<Material> grassBlocks;
    private BukkitRunnable grassCheckerRunnable;

    public GrassChecker(TouchGrass plugin, ChallengePluginAPI challengePluginAPI) {
        this.plugin = plugin;
        this.challengePluginAPI = challengePluginAPI;
        this.grassBlocks = GrassBlocks.getGrassBlocks(plugin);
    }

    public void init() {
        grassCheckerRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!challengePluginAPI.isRunning()) {
                        continue;
                    }
                    if (player.isDead()) {
                        continue;
                    }
                    for (Material grassBlock : grassBlocks) {
                        if (player.getLocation().clone().add(0, -1, 0).getBlock().getType() == grassBlock) {
                            player.setHealth(0.0);
                        }
                    }
                }
            }
        };
        grassCheckerRunnable.runTaskTimer(plugin, 0L, 1L);
    }

    public void cancel() {
        grassCheckerRunnable.cancel();
    }

}
