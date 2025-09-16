package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.BasicAssembler;
import com.github.darksoulq.nem.data.recipe.BasicAssemblerRecipe;

public class BasicAssemblerLayout extends MTRecipeLayout<BasicAssemblerRecipe>{


    public BasicAssemblerLayout() {
        super(new BasicAssembler(null).getDeviceStack());
    }

    @Override
    public Class<BasicAssemblerRecipe> getRecipeClass() {
        return BasicAssemblerRecipe.class;
    }
}
