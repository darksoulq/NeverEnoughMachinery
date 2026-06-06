package com.github.darksoulq.nem.data;

import com.MT.xxxtrigger50xxx.Devices.Device;
import com.MT.xxxtrigger50xxx.Guide.ItemMenu;
import com.MT.xxxtrigger50xxx.Guide.MinetorioTables;
import com.MT.xxxtrigger50xxx.Recipes.MTRecipe;
import com.MT.xxxtrigger50xxx.Recipes.RecipeUtils;
import com.github.darksoulq.abyssallib.world.item.Item;
import com.github.darksoulq.nem.data.recipe.*;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.plugin.Registration;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemLore;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeLoader {
    private static final int[] TARGET_SLOTS = { 11, 12, 13, 20, 21, 22, 29, 30, 31 };

    public static void load(Registration registry) {
        for (ItemStack s : ItemMenu.getAllItems()) {
            registry.addItem("minetorio", s);
        }

        List<MTRecipe> recipes = RecipeUtils.getAllRecipeData();
        recipes.forEach(r -> {
            switch (r.getType()) {
                case CRUDE -> registry.addRecipe(new CrudeAssemblerRecipe(r));
                case BASIC -> registry.addRecipe(new BasicAssemblerRecipe(r));
                case ADVANCED -> registry.addRecipe(new AdvancedAssemblerRecipe(r));
            }
        });

        List<Material> siftables = new ArrayList<>(MinetorioTables.siftResults.keySet());
        siftables.forEach(m -> {
            ItemStack input = ItemStack.of(m);
            List<ItemStack> output = new ArrayList<>(MinetorioTables.siftResults.getOrDefault(m, new HashMap<>()).values());
            registry.addRecipe(new MultiOutputRecipe(input, output));
        });

        List<Material> crushables = MinetorioTables.getCrushables();
        crushables.forEach(m -> {
            registry.addRecipe(new CrusherRecipe(ItemStack.of(m)));
        });
    }

    public static void populateMTRecipeSlots(ParsedRecipeView.Builder builder, MTWrappedRecipe recipe) {
        List<ItemStack> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size() && i < TARGET_SLOTS.length; i++) {
            builder.set(TARGET_SLOTS[i], ingredients.get(i));
        }

        if (recipe.hasLiquid() && recipe.getLiquidAmount() > 0) {
            Device.LiquidType type = recipe.getLiquid();
            int amount = recipe.getLiquidAmount();
            ItemStack liquidIcon = new ItemStack(Material.PAPER);
            switch (type) {
                case SULFURIC_ACID -> setLiquidIcon(liquidIcon, "sulfuric_acid", amount, NamedTextColor.YELLOW);
                case LIGHTOIL -> setLiquidIcon(liquidIcon, "light_oil", amount, NamedTextColor.GRAY);
                case HEAVYOIL -> setLiquidIcon(liquidIcon, "heavy_oil", amount, NamedTextColor.DARK_GRAY);
                case LUBRICANT -> setLiquidIcon(liquidIcon, "lubricant", amount, NamedTextColor.BLACK);
                case PETROL -> setLiquidIcon(liquidIcon, "petrol", amount, NamedTextColor.GOLD);
                case OIL -> setLiquidIcon(liquidIcon, "oil", amount, NamedTextColor.DARK_GRAY);
                case STEAM -> setLiquidIcon(liquidIcon, "steam", amount, NamedTextColor.WHITE);
                case WATER -> setLiquidIcon(liquidIcon, "water", amount, NamedTextColor.BLUE);
            }
            builder.set(18, liquidIcon);
        }

        if (recipe.hasTech()) {
            String tech = recipe.getTech();
            Item techItem = new Item(Key.key("nem", "research"), Material.PAPER);
            Item.Tooltip tl = techItem.tooltip;
            tl.lines.clear();
            tl.addLine(
                Component.translatable("lore.nem.research_required")
                    .color(NamedTextColor.DARK_GREEN)
                    .decoration(TextDecoration.ITALIC, false)
                    .decoration(TextDecoration.BOLD, true)
                    .append(Component.text(": " + tech))
                    .decoration(TextDecoration.ITALIC, false)
                    .decoration(TextDecoration.BOLD, true)
            );
            techItem.updateTooltip();
            builder.set(26, techItem.getStack());
        }

        builder.set(24, recipe.getResult());
    }

    private static void setLiquidIcon(ItemStack stack, String name, int amount, TextColor color) {
        stack.setData(DataComponentTypes.ITEM_MODEL, new NamespacedKey("nem", name));
        stack.setData(DataComponentTypes.ITEM_NAME, Component.translatable("item.nem." + name));
        stack.setData(DataComponentTypes.LORE, ItemLore.lore()
            .lines(List.of(
                Component.translatable("lore.nem.liquid_amount")
                    .color(color)
                    .decoration(TextDecoration.ITALIC, false)
                    .decoration(TextDecoration.BOLD, true)
                    .append(Component.text(": " + amount))
                    .decoration(TextDecoration.ITALIC, false)
                    .decoration(TextDecoration.BOLD, true)
            ))
            .build());
    }
}