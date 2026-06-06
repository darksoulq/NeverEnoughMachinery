package com.github.darksoulq.nem;

import com.MT.xxxtrigger50xxx.Guide.ItemMenu;
import com.github.darksoulq.abyssallib.server.event.EventBus;
import com.github.darksoulq.abyssallib.server.event.SubscribeEvent;
import com.github.darksoulq.nem.data.Pack;
import com.github.darksoulq.nem.data.RecipeLoader;
import com.github.darksoulq.nem.layout.*;
import com.github.darksoulq.ner.NeverEnoughRecipes;
import com.github.darksoulq.ner.plugin.NerRegistrationEvent;
import com.github.darksoulq.ner.plugin.Registration;
import org.bukkit.plugin.java.JavaPlugin;

public final class NeverEnoughMachinery extends JavaPlugin {

    @Override
    public void onEnable() {
        Pack.init(this);
        new EventBus(this).register(this);
        NeverEnoughRecipes.registerPlugin(new NemIntegration());
    }
}