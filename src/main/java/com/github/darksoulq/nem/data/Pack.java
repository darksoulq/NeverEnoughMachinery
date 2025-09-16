package com.github.darksoulq.nem.data;

import com.github.darksoulq.abyssallib.server.resource.Namespace;
import com.github.darksoulq.abyssallib.server.resource.ResourcePack;
import com.github.darksoulq.abyssallib.server.resource.asset.Font;
import com.github.darksoulq.abyssallib.server.resource.asset.Lang;
import com.github.darksoulq.abyssallib.server.resource.asset.Model;
import com.github.darksoulq.abyssallib.server.resource.asset.Texture;
import com.github.darksoulq.abyssallib.server.resource.asset.definition.Selector;
import org.bukkit.plugin.Plugin;

public class Pack {
    public static Font.Glyph MULTI_OUTPUT;
    public static void init(Plugin plugin) {
        ResourcePack pack = new ResourcePack(plugin, "nem");
        Namespace ns = pack.namespace("nem");

        Font fn = ns.font("gui", false);
        MULTI_OUTPUT = fn.glyph(ns.texture("gui/multi_output"), 222, 13);

        Lang en = ns.lang("en_us", false);
        en.put("lore.nem.research_required", "Required");
        en.put("lore.nem.liquid_amount", "Amount");
        en.put("item.nem.research", "Research");
        en.put("item.nem.water", "Water");
        en.put("item.nem.steam", "Steam");
        en.put("item.nem.oil", "Oil");
        en.put("item.nem.light_oil", "Light Oil");
        en.put("item.nem.heavy_oil", "Heavy Oil");
        en.put("item.nem.petrol", "Petrol");
        en.put("item.nem.lubricant", "Lubricant");
        en.put("item.nem.sulfuric_acid", "Sulphuric Acid");

        createItemDef(ns, "water");
        createItemDef(ns, "oil");
        createItemDef(ns, "heavy_oil");
        createItemDef(ns, "light_oil");
        createItemDef(ns, "petrol");
        createItemDef(ns, "lubricant");
        createItemDef(ns, "sulfuric_acid");
        createItemDef(ns, "steam");
        createItemDef(ns, "research");

        pack.register(false);
    }

    private static void createItemDef(Namespace ns, String name) {
        Texture tex = ns.texture("item/" + name);
        Model model = ns.model(name, false);
        model.parent("minecraft:item/generated");
        model.texture("layer0", tex);

        Selector.Model sel = new Selector.Model(model);
        ns.itemDefinition(name, sel, true);
        ns.mcmeta("item/" + name, true);
    }
}
