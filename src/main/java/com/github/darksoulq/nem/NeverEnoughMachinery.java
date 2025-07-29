package com.github.darksoulq.nem;

import com.github.darksoulq.nem.data.Pack;
import com.github.darksoulq.nem.data.RecipeLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class NeverEnoughMachinery extends JavaPlugin {

    @Override
    public void onEnable() {
        Pack.init(this);
        new BukkitRunnable() {
            @Override
            public void run() {
                RecipeLoader.init();
            }
        }.runTaskLater(this, 10);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
