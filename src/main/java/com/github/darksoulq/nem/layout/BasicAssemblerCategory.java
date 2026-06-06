package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.BasicAssembler;
import com.github.darksoulq.nem.data.RecipeLoader;
import com.github.darksoulq.nem.data.recipe.BasicAssemblerRecipe;
import com.github.darksoulq.ner.layout.RecipeCategory;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.resources.Pack;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class BasicAssemblerCategory extends RecipeCategory<BasicAssemblerRecipe> {
    private final ItemStack device = new BasicAssembler(null).getDeviceStack();

    @Override
    public Class<BasicAssemblerRecipe> getRecipeClass() {
        return BasicAssemblerRecipe.class;
    }

    @Override
    public ParsedRecipeView parseRecipe(BasicAssemblerRecipe recipe, ItemStack catalyst) {
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