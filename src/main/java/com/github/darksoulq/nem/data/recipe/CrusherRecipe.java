package com.github.darksoulq.nem.data.recipe;

import com.MT.xxxtrigger50xxx.Guide.MinetorioTables;
import com.github.darksoulq.ner.layout.RecipeLayout;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CrusherRecipe {
    private final ItemStack input;
    private final ItemStack output;

    public CrusherRecipe(ItemStack input) {
        int required = MinetorioTables.crushCount(input);
        this.input = input.clone();
        this.input.setAmount(required);
        this.output = MinetorioTables.crushStack(this.input);
    }

    public @NotNull ItemStack getInput() {
        return input;
    }
    public @NotNull ItemStack getOutput() {
        return output;
    }
}
