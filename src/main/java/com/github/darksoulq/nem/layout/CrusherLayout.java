package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.Crusher;
import com.github.darksoulq.nem.data.recipe.CrusherRecipe;
import com.github.darksoulq.ner.layout.PaginatedSection;
import com.github.darksoulq.ner.layout.RecipeLayout;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.resources.Pack;
import com.github.darksoulq.ner.resources.UiItems;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CrusherLayout extends RecipeLayout<CrusherRecipe> {
    @Override
    public Class<CrusherRecipe> getRecipeClass() {
        return CrusherRecipe.class;
    }

    @Override
    public ParsedRecipeView parseRecipe(CrusherRecipe recipe) {
        Map<Integer, List<ItemStack>> slotMap = new HashMap<>();
        slotMap.put(20, List.of(recipe.getInput()));
        slotMap.put(24, List.of(recipe.getOutput()));
        return new ParsedRecipeView(slotMap, Pack.STONE_CUTTER, -8, new Crusher(null).getDeviceStack());
    }

    @Override
    public Set<Integer> getOutputSlots() {
        return Set.of(24);
    }
}
