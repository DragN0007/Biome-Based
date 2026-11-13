package com.dragn0007.biome_based.mixin;

import com.dragn0007.biome_based.util.BasedCommonConfig;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlowerBlock.class)
public abstract class FlowerBlockMixin extends BushBlock implements SuspiciousEffectHolder {
    public FlowerBlockMixin(Properties properties) {super(properties);}

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return (BasedCommonConfig.CRUSH_FLOWERS.get() && !context.getItemInHand().is(ItemTags.FLOWERS)) || super.canBeReplaced(state, context);
    }
}