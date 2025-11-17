package punky.stone.touchgrass;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class GrassBlocks {
    public static List<Material> getGrassBlocks(TouchGrass plugin) {
        plugin.saveDefaultConfig();
        List<String> blockNames = plugin.getConfig().getStringList("grass-blocks");
        List<Material> grassBlocks = new ArrayList<>();
        for (String blockName : blockNames) {
            try {
                grassBlocks.add(Material.valueOf(blockName.toUpperCase()));
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Invalid material in config: " + blockName);
            }
        }
        return grassBlocks;
    }
}
