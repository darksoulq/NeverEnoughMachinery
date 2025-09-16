package com.github.darksoulq.nem.data;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.CrudeAssembler;
import com.MT.xxxtrigger50xxx.Guide.ItemMenu;
import com.MT.xxxtrigger50xxx.Recipes.MTRecipe;
import com.MT.xxxtrigger50xxx.Recipes.RecipeUtils;
import com.github.darksoulq.nem.data.recipe.AdvancedAssemblerRecipe;
import com.github.darksoulq.nem.data.recipe.BasicAssemblerRecipe;
import com.github.darksoulq.nem.data.recipe.CrudeAssemblerRecipe;
import com.github.darksoulq.nem.data.recipe.MTWrappedRecipe;
import com.github.darksoulq.nem.layout.AdvancedAssemblerLayout;
import com.github.darksoulq.nem.layout.BasicAssemblerLayout;
import com.github.darksoulq.nem.layout.CrudeAssemblerLayout;
import com.github.darksoulq.ner.NerApi;
import com.github.darksoulq.ner.layout.RecipeLayoutRegistry;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RecipeLoader {
    public static void init() {
        RecipeLayoutRegistry.register(new AdvancedAssemblerLayout());
        RecipeLayoutRegistry.register(new CrudeAssemblerLayout());
        RecipeLayoutRegistry.register(new BasicAssemblerLayout());
        ItemStack crudeAss = new CrudeAssembler(null).getDeviceStack();
        for (ItemStack s : ItemMenu.getAllItems()) {
            if (!s.isSimilar(crudeAss)) NerApi.addItem(s);
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
    }

    private static void addRecipe(MTWrappedRecipe recipe, ItemStack result) {
        NerApi.registerRecipe(result, recipe);
    }
}
