package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.BasicAssembler;
import com.github.darksoulq.nem.data.BasicAssemblerRecipe;
import org.bukkit.inventory.ItemStack;

public class BasicAssemblerLayout extends MTRecipeLayout<BasicAssemblerRecipe>{


    public BasicAssemblerLayout() {
        super(new BasicAssembler(null).getDeviceStack());
    }

    @Override
    public Class<BasicAssemblerRecipe> getRecipeClass() {
        return BasicAssemblerRecipe.class;
    }
}
