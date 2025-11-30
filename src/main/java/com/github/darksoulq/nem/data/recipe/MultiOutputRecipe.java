package com.github.darksoulq.nem.data.recipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MultiOutputRecipe {
    private final ItemStack input;
    private final List<ItemStack> results = new ArrayList<>();

    public MultiOutputRecipe(ItemStack input, List<ItemStack> result) {
        this.input = input;
        this.results.addAll(result);
    }

    public ItemStack getInput() {
        return input;
    }
    public List<ItemStack> getOutput() {
        return results;
    }


















}
