package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.AdvancedAssembler;
import com.github.darksoulq.nem.data.RecipeLoader;
import com.github.darksoulq.nem.data.recipe.AdvancedAssemblerRecipe;
import com.github.darksoulq.ner.layout.RecipeCategory;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.resources.Pack;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class AdvancedAssemblerCategory extends RecipeCategory<AdvancedAssemblerRecipe> {
    private final ItemStack device = new AdvancedAssembler(null).getDeviceStack();

    @Override
    public Class<AdvancedAssemblerRecipe> getRecipeClass() {
        return AdvancedAssemblerRecipe.class;
    }

    @Override
    public ParsedRecipeView parseRecipe(AdvancedAssemblerRecipe recipe, ItemStack catalyst) {
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