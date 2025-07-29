package com.github.darksoulq.nem.data;

import com.MT.xxxtrigger50xxx.Devices.Device;
import com.MT.xxxtrigger50xxx.Recipes.MTRecipe;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MTWrappedRecipe {
    private final ItemStack result;
    private final List<ItemStack> ingredients;
    private final Device.LiquidType liquid;
    private final int liquidAmount;
    private final String tech;

    public MTWrappedRecipe(MTRecipe recipe) {
        result =  recipe.getResult();
        ingredients = recipe.getIngredients();
        liquid = recipe.getRecipeLiquid();
        liquidAmount = recipe.getLiquidAmount();
        tech = recipe.getRequiredTech();
    }

    public ItemStack getResult() {
        return result;
    }

    public List<ItemStack> getIngredients() {
        return ingredients;
    }

    public Device.LiquidType getLiquid() {
        return liquid;
    }

    public int getLiquidAmount() {
        return liquidAmount;
    }

    public String getTech() {
        return tech;
    }

    public boolean hasTech() {
        return tech != null && !tech.isBlank();
    }

    public boolean hasLiquid() {
        return liquid != null;
    }
}
