package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.Sifter;
import com.github.darksoulq.nem.data.recipe.SifterRecipe;

public class SifterLayout extends MultiOutputLayout<SifterRecipe> {
    public SifterLayout() {
        super(new Sifter(null).getDeviceStack());
    }

    @Override
    public Class<SifterRecipe> getRecipeClass() {
        return SifterRecipe.class;
    }
}
