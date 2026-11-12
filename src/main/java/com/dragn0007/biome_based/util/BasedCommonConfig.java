package com.dragn0007.biome_based.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class BasedCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Double> FAIL_GROWTH_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SLOWER_CROP_GROWTH;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SLOWER_CROP_GROWTH_GREENHOUSE;
    public static final ForgeConfigSpec.ConfigValue<Double> FAIL_GROWTH_CHANCE_OVERALL;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SLOWER_CROP_GROWTH_DRY;
    public static final ForgeConfigSpec.ConfigValue<Boolean> GREENHOUSE;

    static {
        BUILDER.push("Crops");
        FAIL_GROWTH_CHANCE = BUILDER.comment("The chance at which a crop fails to grow outside of it's natural biome zone each growth tick. Default is 0.75 (75%).")
                .define("Crop Fail Growth Chance", 0.75);
        SLOWER_CROP_GROWTH = BUILDER.comment("Should crops grow slower overall?")
                .define("Slower Overall Crop Growth", false);
        SLOWER_CROP_GROWTH_GREENHOUSE = BUILDER.comment("Should crops grow slower even in greenhouses? (Basically, greenhouses will only help with biome-based growth).")
                .define("Slower Overall Crop Growth in Greenhouses", true);
        FAIL_GROWTH_CHANCE_OVERALL = BUILDER.comment("The chance at which a crop fails to grow overall. Default is 0.50 (50%).")
                .define("Overall Crop Fail Growth Chance", 0.50);
        SLOWER_CROP_GROWTH_DRY = BUILDER.comment("Should crops always grow slower in dry biomes?")
                .define("Slower Overall Crop Growth", true);
        GREENHOUSE = BUILDER.comment("Should crops be able to grow normally under glass/ tagged greenhouse blocks?")
                .define("Greenhouse Blocks Enabled", true);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
