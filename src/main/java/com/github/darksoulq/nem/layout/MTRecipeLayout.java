package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Device;
import com.github.darksoulq.abyssallib.common.util.Identifier;
import com.github.darksoulq.abyssallib.world.item.Item;
import com.github.darksoulq.nem.data.recipe.MTWrappedRecipe;
import com.github.darksoulq.ner.layout.RecipeLayout;
import com.github.darksoulq.ner.model.ParsedRecipeView;
import com.github.darksoulq.ner.resources.Pack;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemLore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class MTRecipeLayout<T extends MTWrappedRecipe> extends RecipeLayout<T> {
    private final static int[] TARGET_SLOTS = {
            11, 12, 13,
            20, 21, 22,
            29, 30, 31
    };
    private final ItemStack device;

    public MTRecipeLayout(ItemStack device) {
        this.device = device;
    }

    @Override
    public ParsedRecipeView parseRecipe(T recipe) {
        return new ParsedRecipeView(handleParse(recipe), Pack.CRAFTING_TABLE, -8, device);
    }

    public Map<Integer, List<ItemStack>> handleParse(T recipe) {
        Map<Integer, List<ItemStack>> slotMap = new HashMap<>();
        List<ItemStack> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            slotMap.put(TARGET_SLOTS[i], List.of(ingredients.get(i)));
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
            slotMap.put(27, List.of(liquidIcon));
        }
        if (recipe.hasTech()) {
            String tech = recipe.getTech();
            Item techItem = new Item(Identifier.of("nem", "research"), Material.PAPER);
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
            slotMap.put(35, List.of(techItem.getStack()));
        }
        slotMap.put(24, List.of(recipe.getResult()));
        return slotMap;
    }

    @Override
    public Set<Integer> getOutputSlots() {
        return Set.of();
    }

    private void setLiquidIcon(ItemStack stack, String name, int amount, TextColor color) {
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
