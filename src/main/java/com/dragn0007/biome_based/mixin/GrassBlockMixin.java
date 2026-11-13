package com.dragn0007.biome_based.mixin;

import com.dragn0007.biome_based.util.BBTags;
import com.dragn0007.biome_based.util.BasedCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Mixin(GrassBlock.class)
public abstract class GrassBlockMixin extends SpreadingSnowyDirtBlock implements BonemealableBlock {
    public GrassBlockMixin(Properties properties) {super(properties);}

    /**
     * @author DragN0007
     * @reason Prevents flowers from spawning from bonemeal outside of flower-based biomes
     */
    @Overwrite
    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = Blocks.GRASS.defaultBlockState();
        Optional<Holder.Reference<PlacedFeature>> optional = level.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(VegetationPlacements.GRASS_BONEMEAL);
        label49:
        for(int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j) {
                blockpos1 = blockpos1.offset(source.nextInt(3) - 1, (source.nextInt(3) - 1) * source.nextInt(3) / 2, source.nextInt(3) - 1);
                if (!level.getBlockState(blockpos1.below()).is(this) || level.getBlockState(blockpos1).isCollisionShapeFullBlock(level, blockpos1))
                    continue label49;
            }

            BlockState blockstate1 = level.getBlockState(blockpos1);
            if (blockstate1.is(blockstate.getBlock()) && source.nextInt(10) == 0) {
                ((BonemealableBlock)blockstate.getBlock()).performBonemeal(level, source, blockpos1, blockstate1);
            }

            if (blockstate1.isAir()) {
                Holder<PlacedFeature> holder;
                //prevent spawning of random flowers unless in flower forest or sunflower plains
                if (source.nextInt(8) == 0 && ((level.getBiome(pos).is(Biomes.FLOWER_FOREST) || level.getBiome(pos).is(Biomes.SUNFLOWER_PLAINS)) || !BasedCommonConfig.FLOWER_BIOME_BONEMEAL.get())) {
                    List<ConfiguredFeature<?, ?>> list = level.getBiome(blockpos1).value().getGenerationSettings().getFlowerFeatures();
                    if (list.isEmpty()) continue;
                    holder = ((RandomPatchConfiguration) list.get(0).config()).feature();
                } else {
                    if (optional.isEmpty()) continue;
                    holder = optional.get();
                }
                holder.value().place(level, level.getChunkSource().getGenerator(), source, blockpos1);
            }
        }
    }
}