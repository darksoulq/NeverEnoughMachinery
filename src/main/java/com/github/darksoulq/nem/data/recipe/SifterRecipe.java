package com.github.darksoulq.nem.data.recipe;

import com.MT.xxxtrigger50xxx.Guide.MinetorioTables;
import com.MT.xxxtrigger50xxx.Guide.TablesMenu;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SifterRecipe extends MultiOutputRecipe {
    public SifterRecipe(ItemStack input) {
        super(input, MinetorioTables.getPossibleResults(input));
    }
}
