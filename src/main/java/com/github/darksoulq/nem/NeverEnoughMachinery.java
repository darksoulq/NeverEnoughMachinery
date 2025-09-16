package com.github.darksoulq.nem;

import com.github.darksoulq.nem.data.Pack;
import com.github.darksoulq.nem.data.RecipeLoader;
import com.github.darksoulq.ner.data.RecipeManager;
import com.github.darksoulq.ner.gui.MainMenu;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public final class NeverEnoughMachinery extends JavaPlugin {

    @Override
    public void onEnable() {
        Pack.init(this);
        new BukkitRunnable() {
            @Override
            public void run() {
                RecipeLoader.init();
                MainMenu.ITEMS = MainMenu.sortDisplay(new ArrayList<>(RecipeManager.getAllItems()));
            }
        }.runTaskLater(this, 10);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
