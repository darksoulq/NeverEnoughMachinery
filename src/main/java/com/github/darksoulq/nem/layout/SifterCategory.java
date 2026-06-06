package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.Sifter;
import com.MT.xxxtrigger50xxx.Guide.MinetorioTables;
import com.github.darksoulq.nem.data.Pack;
import com.github.darksoulq.nem.data.recipe.MultiOutputRecipe;
import com.github.darksoulq.ner.layout.RecipeCategory;
import com.github.darksoulq.ner.model.PagedSection;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.model.SectionButton;
import com.github.darksoulq.ner.resources.UiItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SifterCategory extends RecipeCategory<MultiOutputRecipe> {
    private static final int[] SLOTS = new int[] {19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};
    private final ItemStack device = new Sifter(null).getDeviceStack();

    @Override
    public Class<MultiOutputRecipe> getRecipeClass() {
        return MultiOutputRecipe.class;
    }

    @Override
    public ParsedRecipeView parseRecipe(MultiOutputRecipe recipe, ItemStack catalyst) {
        ParsedRecipeView.Builder builder = ParsedRecipeView.builder(Pack.MULTI_OUTPUT, -8, device)
            .set(4, recipe.getInput());

        Material inputMat = recipe.getInput().getType();
        HashMap<Material, Integer> chances = MinetorioTables.siftChances.get(inputMat);

        for (ItemStack item : recipe.getOutput()) {
            Material outMat = item.getType();
            if (chances != null && chances.containsKey(outMat)) {
                builder.probability(item, chances.get(outMat) + "%");
            }
        }

        builder.addSection(new PagedSection(
            SLOTS,
            recipe.getOutput(),
            new SectionButton(27, UiItems.PREV.getStack().clone()),
            new SectionButton(35, UiItems.NEXT.getStack().clone())
        ));

        return builder.build();
    }

    @Override
    public Set<Integer> getResultSlots() {
        Set<Integer> ret = new HashSet<>();
        for (int slot : SLOTS) {
            ret.add(slot);
        }
        return ret;
    }

    @Override
    public Set<Integer> getIgnoredSlots() {
        return Set.of(27, 35);
    }
}