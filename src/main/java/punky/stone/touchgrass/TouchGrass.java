package punky.stone.touchgrass;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import punky.stone.challengeplugin.ChallengePluginAPI;

public final class TouchGrass extends JavaPlugin {
    private GrassChecker grassChecker;

    @Override
    public void onEnable() {
        RegisteredServiceProvider<ChallengePluginAPI> provider =
                getServer().getServicesManager().getRegistration(ChallengePluginAPI.class);

        if (provider == null) {
            getLogger().warning("Could not find ChallengePlugin!");
            return;
        }
        ChallengePluginAPI challengePluginAPI = provider.getProvider();
        MovementCancel movementCancel = new MovementCancel(this);
        movementCancel.init();
        challengePluginAPI.registerChallengeEvents(movementCancel);
        grassChecker = new GrassChecker(this, challengePluginAPI);
        grassChecker.init();
        SpawnHandler spawnHandler = new SpawnHandler(this, challengePluginAPI);
        spawnHandler.init();
    }

    @Override
    public void onDisable() {
        grassChecker.cancel();
    }

}
