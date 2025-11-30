package com.github.darksoulq.nem.layout;

import com.github.darksoulq.nem.data.Pack;
import com.github.darksoulq.nem.data.recipe.MultiOutputRecipe;
import com.github.darksoulq.ner.layout.PaginatedSection;
import com.github.darksoulq.ner.layout.RecipeLayout;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.resources.UiItems;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public abstract class MultiOutputLayout<T extends MultiOutputRecipe> extends RecipeLayout<T> {
    private static final int[] SLOTS = new int[] {19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};
    private final ItemStack device;

    public MultiOutputLayout(ItemStack device) {
        this.device = device;
    }

    @Override
    public ParsedRecipeView parseRecipe(MultiOutputRecipe recipe) {
        Map<Integer, List<ItemStack>> slotMap = new HashMap<>();
        slotMap.put(4, List.of(recipe.getInput()));
        PaginatedSection section = new PaginatedSection(SLOTS, recipe.getOutput(),
                new PaginatedSection.Button(UiItems.NEXT.get().getStack(), 44),
                new PaginatedSection.Button(UiItems.PREV.get().getStack(), 44 - 8));
        return new ParsedRecipeView(slotMap, Pack.MULTI_OUTPUT, -8, device, List.of(section));
    }

    @Override
    public Set<Integer> getOutputSlots() {
        Set<Integer> ret = new HashSet<>();
        for (int slot : SLOTS) {
            ret.add(slot);
        }
        return ret;
    }
}
