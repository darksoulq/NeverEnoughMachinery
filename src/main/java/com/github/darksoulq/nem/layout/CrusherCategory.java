package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.Crusher;
import com.github.darksoulq.nem.data.recipe.CrusherRecipe;
import com.github.darksoulq.ner.layout.RecipeCategory;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.resources.Pack;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.Set;

public class CrusherCategory extends RecipeCategory<CrusherRecipe> {
    private final ItemStack device = new Crusher(null).getDeviceStack();

    @Override
    public Class<CrusherRecipe> getRecipeClass() {
        return CrusherRecipe.class;
    }

    @Override
    public ParsedRecipeView parseRecipe(CrusherRecipe recipe, ItemStack catalyst) {
        return ParsedRecipeView.builder(Pack.STONE_CUTTER, -8, device)
            .set(20, recipe.getInput())
            .set(24, recipe.getOutput())
            .build();
    }

    @Override
    public Set<Integer> getResultSlots() {
        return Set.of(24);
    }

    @Override
    public Set<Integer> getIgnoredSlots() {
        return Collections.emptySet();
    }
}