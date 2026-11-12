package com.dragn0007.biome_based;

import com.dragn0007.biome_based.util.BasedCommonConfig;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(BiomeBased.MODID)
public class BiomeBased {
    public static final String MODID = "biome_based";

    public BiomeBased() {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BasedCommonConfig.SPEC, "biome-based-common.toml");
    }
}