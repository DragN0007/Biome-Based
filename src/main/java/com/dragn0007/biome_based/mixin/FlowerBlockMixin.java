package com.dragn0007.biome_based.mixin;

import com.dragn0007.biome_based.util.BasedCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;
import java.util.Optional;

@Mixin(FlowerBlock.class)
public abstract class FlowerBlockMixin extends BushBlock implements SuspiciousEffectHolder {
    public FlowerBlockMixin(Properties properties) {super(properties);}

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return (BasedCommonConfig.CRUSH_FLOWERS.get() && !context.getItemInHand().is(ItemTags.FLOWERS)) || super.canBeReplaced(state, context);
    }
}