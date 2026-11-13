package com.dragn0007.biome_based.mixin;

import com.dragn0007.biome_based.util.BasedCommonConfig;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DoublePlantBlock.class)
public abstract class DoublePlantBlockMixin extends BushBlock {
    public DoublePlantBlockMixin(Properties properties) {super(properties);}

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return (BasedCommonConfig.CRUSH_DOUBLE_PLANTS.get() && !context.getItemInHand().is(ItemTags.TALL_FLOWERS)) || super.canBeReplaced(state, context);
    }
}