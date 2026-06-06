package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.CrudeAssembler;
import com.github.darksoulq.nem.data.RecipeLoader;
import com.github.darksoulq.nem.data.recipe.CrudeAssemblerRecipe;
import com.github.darksoulq.ner.layout.RecipeCategory;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.resources.Pack;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class CrudeAssemblerCategory extends RecipeCategory<CrudeAssemblerRecipe> {
    private final ItemStack device = new CrudeAssembler(null).getDeviceStack();

    @Override
    public Class<CrudeAssemblerRecipe> getRecipeClass() {
        return CrudeAssemblerRecipe.class;
    }

    @Override
    public ParsedRecipeView parseRecipe(CrudeAssemblerRecipe recipe, ItemStack catalyst) {
        ParsedRecipeView.Builder builder = ParsedRecipeView.builder(Pack.CRAFTING_TABLE, -8, device);
        RecipeLoader.populateMTRecipeSlots(builder, recipe);
        return builder.build();
    }

    @Override
    public Set<Integer> getResultSlots() {
        return Set.of(24);
    }

    @Override
    public Set<Integer> getIgnoredSlots() {
        return Set.of(18, 26);
    }
}