package com.github.darksoulq.nem.layout;

import com.MT.xxxtrigger50xxx.Devices.Manufactoring.CrudeAssembler;
import com.github.darksoulq.nem.data.CrudeAssemblerRecipe;

public class CrudeAssemblerLayout extends MTRecipeLayout<CrudeAssemblerRecipe> {

    public CrudeAssemblerLayout() {
        super(new CrudeAssembler(null).getDeviceStack());
    }

    @Override
    public Class<CrudeAssemblerRecipe> getRecipeClass() {
        return CrudeAssemblerRecipe.class;
    }
}
