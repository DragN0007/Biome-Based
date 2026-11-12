package com.dragn0007.biome_based.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BBTags {
    public static class Blocks {
        //Please do not add the same crops to multiple tags. It completely breaks their growth, I assume because of the way the mixin is cancelled.
        public static final TagKey<Block> GROWS_IN_ANY = forgeTag("grows_in_any");
        public static final TagKey<Block> GROWS_IN_COLD = forgeTag("grows_in_cold");
        public static final TagKey<Block> GROWS_IN_DENSE = forgeTag("grows_in_dense");
        public static final TagKey<Block> GROWS_IN_HOT = forgeTag("grows_in_hot");
        public static final TagKey<Block> GROWS_IN_PLAINS = forgeTag("grows_in_plains");
        public static final TagKey<Block> GROWS_IN_WET = forgeTag("grows_in_wet");
        public static final TagKey<Block> GREENHOUSE_BLOCKS = forgeTag("greenhouse_blocks");
        public static TagKey<Block> forgeTag (String name) {return BlockTags.create(new ResourceLocation("forge", name));}
    }
}
