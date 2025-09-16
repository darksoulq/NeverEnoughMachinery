package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.AdvancedAssembler;
import com.github.darksoulq.nem.data.recipe.AdvancedAssemblerRecipe;

public class AdvancedAssemblerLayout extends MTRecipeLayout<AdvancedAssemblerRecipe> {
    public AdvancedAssemblerLayout() {
        super(new AdvancedAssembler(null).getDeviceStack());
    }

    @Override
    public Class<AdvancedAssemblerRecipe> getRecipeClass() {
        return AdvancedAssemblerRecipe.class;
    }
}
