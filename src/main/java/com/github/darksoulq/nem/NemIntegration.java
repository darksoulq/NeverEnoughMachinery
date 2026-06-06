package com.github.darksoulq.nem;

import com.github.darksoulq.nem.data.RecipeLoader;
import com.github.darksoulq.nem.layout.*;
import com.github.darksoulq.ner.plugin.NerPlugin;
import com.github.darksoulq.ner.plugin.Registration;

public class NemIntegration implements NerPlugin {
    @Override
    public void register(Registration registry) {
        registry.addCategory(new AdvancedAssemblerCategory());
        registry.addCategory(new BasicAssemblerCategory());
        registry.addCategory(new CrudeAssemblerCategory());
        registry.addCategory(new CrusherCategory());
        registry.addCategory(new SifterCategory());

        RecipeLoader.load(registry);
    }
}