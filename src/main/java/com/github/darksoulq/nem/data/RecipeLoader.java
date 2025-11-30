package com.github.darksoulq.nem.data;

import com.MT.xxxtrigger50xxx.Guide.ItemMenu;
import com.MT.xxxtrigger50xxx.Guide.MinetorioTables;
import com.MT.xxxtrigger50xxx.Recipes.MTRecipe;
import com.MT.xxxtrigger50xxx.Recipes.RecipeUtils;
import com.github.darksoulq.nem.data.recipe.*;
import com.github.darksoulq.nem.layout.*;
import com.github.darksoulq.ner.NerApi;
import com.github.darksoulq.ner.layout.RecipeLayoutRegistry;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RecipeLoader {
    public static void init() {
        RecipeLayoutRegistry.register(new AdvancedAssemblerLayout());
        RecipeLayoutRegistry.register(new CrudeAssemblerLayout());
        RecipeLayoutRegistry.register(new BasicAssemblerLayout());
        RecipeLayoutRegistry.register(new CrusherLayout());
        RecipeLayoutRegistry.register(new SifterLayout());
        for (ItemStack s : ItemMenu.getAllItems()) {
            NerApi.addItemToNamespace("minetorio", s);
        }

        List<MTRecipe> recipes = RecipeUtils.getAllRecipeData();
        recipes.forEach(r -> {
            switch (r.getType()) {
                case BASIC -> addRecipe(new BasicAssemblerRecipe(r), r.getResult());
                case CRUDE -> addRecipe(new CrudeAssemblerRecipe(r), r.getResult());
                case ADVANCED -> addRecipe(new AdvancedAssemblerRecipe(r), r.getResult());
            }
        });

        List<Material> siftables = MinetorioTables.getSiftable();
        siftables.forEach(m -> {
            SifterRecipe r = new SifterRecipe(ItemStack.of(m));
            for (ItemStack res : r.getOutput()) addRecipe(r, res);
        });
        List<Material> crushables = MinetorioTables.getCrushables();
        crushables.forEach(m -> {
            CrusherRecipe r = new CrusherRecipe(ItemStack.of(m));
            addRecipe(r, r.getOutput());
        });
    }

    private static void addRecipe(MTWrappedRecipe recipe, ItemStack result) {
        NerApi.registerRecipe(result, recipe);
    }
    private static void addRecipe(MultiOutputRecipe recipe, ItemStack result) {
        NerApi.registerRecipe(result, recipe);
    }
    private static void addRecipe(CrusherRecipe recipe, ItemStack result) {
        NerApi.registerRecipe(result, recipe);
    }
}
