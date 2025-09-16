package com.github.darksoulq.nem.layout;

import com.github.darksoulq.nem.data.recipe.MultiOutputRecipe;
import com.github.darksoulq.ner.layout.RecipeLayout;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.resources.Pack;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class MultiOutputLayout<T extends MultiOutputRecipe> extends RecipeLayout<T> {
    private final int[] TARGET_SLOTS = {21, 23};
    private final ItemStack device;

    public MultiOutputLayout(ItemStack device) {
        this.device = device;
    }

    @Override
    public ParsedRecipeView parseRecipe(MultiOutputRecipe recipe) {
        Map<Integer, List<ItemStack>> slotMap = new HashMap<>();
        slotMap.put(TARGET_SLOTS[0], List.of(recipe.getInput()));
        slotMap.put(TARGET_SLOTS[1], recipe.getOutput());
        return new ParsedRecipeView(slotMap, Pack.STONE_CUTTER, -8, device);
    }

    @Override
    public Set<Integer> getOutputSlots() {
        return Set.of(TARGET_SLOTS[1]);
    }
}
