package com.dragn0007.biome_based.mixin;

import com.dragn0007.biome_based.util.BBTags;
import com.dragn0007.biome_based.util.BasedCommonConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends BushBlock implements BonemealableBlock {
    public CropBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source, CallbackInfo ci) {
        Random random = new Random();
        BlockPos above = pos.above();
        BlockPos above2 = pos.above().above();
        BlockPos above3 = pos.above().above().above();
        BlockPos above4 = pos.above().above().above().above(); //im kinda stupid sorry there's probably a better way to do this
        boolean isInGreenhouse = (level.getBlockState(above).is(BBTags.Blocks.GREENHOUSE_BLOCKS) ||
                level.getBlockState(above2).is(BBTags.Blocks.GREENHOUSE_BLOCKS) ||
                level.getBlockState(above3).is(BBTags.Blocks.GREENHOUSE_BLOCKS) ||
                level.getBlockState(above4).is(BBTags.Blocks.GREENHOUSE_BLOCKS)) &&
                BasedCommonConfig.GREENHOUSE.get() && level.canSeeSky(pos);

        if (!isInGreenhouse) {
            if (random.nextDouble() <= BasedCommonConfig.FAIL_GROWTH_CHANCE.get()) {
                if (level.getBiome(pos).is(Tags.Biomes.IS_DRY_OVERWORLD) && BasedCommonConfig.SLOWER_CROP_GROWTH_DRY.get()) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_COLD_OVERWORLD) && state.is(BBTags.Blocks.GROWS_IN_COLD)) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_DENSE_OVERWORLD) && state.is(BBTags.Blocks.GROWS_IN_DENSE)) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_HOT_OVERWORLD) && state.is(BBTags.Blocks.GROWS_IN_HOT)) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_PLAINS) && state.is(BBTags.Blocks.GROWS_IN_PLAINS)) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_WET_OVERWORLD) && state.is(BBTags.Blocks.GROWS_IN_WET)) ci.cancel();
            } else if (BasedCommonConfig.SLOWER_CROP_GROWTH.get() && random.nextDouble() <= BasedCommonConfig.FAIL_GROWTH_CHANCE_OVERALL.get()) {
                ci.cancel();
            }
        } else {
            if ((BasedCommonConfig.SLOWER_CROP_GROWTH.get() && random.nextDouble() <= BasedCommonConfig.FAIL_GROWTH_CHANCE_OVERALL.get()) && BasedCommonConfig.SLOWER_CROP_GROWTH_GREENHOUSE.get()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "growCrops", at = @At("HEAD"), cancellable = true)
    private void onGrow(Level level, BlockPos pos, BlockState state, CallbackInfo ci) {
        Random random = new Random();
        BlockPos above = pos.above();
        BlockPos above2 = pos.above().above();
        BlockPos above3 = pos.above().above().above();
        BlockPos above4 = pos.above().above().above().above();
        boolean isInGreenhouse = (level.getBlockState(above).is(BBTags.Blocks.GREENHOUSE_BLOCKS) ||
                level.getBlockState(above2).is(BBTags.Blocks.GREENHOUSE_BLOCKS) ||
                level.getBlockState(above3).is(BBTags.Blocks.GREENHOUSE_BLOCKS) ||
                level.getBlockState(above4).is(BBTags.Blocks.GREENHOUSE_BLOCKS)) &&
                BasedCommonConfig.GREENHOUSE.get() && level.canSeeSky(pos);

        if (!isInGreenhouse) {
            if (random.nextDouble() <= BasedCommonConfig.FAIL_GROWTH_CHANCE.get()) {
                if (level.getBiome(pos).is(Tags.Biomes.IS_DRY_OVERWORLD) && BasedCommonConfig.SLOWER_CROP_GROWTH_DRY.get()) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_COLD_OVERWORLD) && state.is(BBTags.Blocks.GROWS_IN_COLD)) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_DENSE_OVERWORLD) && state.is(BBTags.Blocks.GROWS_IN_DENSE)) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_HOT_OVERWORLD) && state.is(BBTags.Blocks.GROWS_IN_HOT)) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_PLAINS) && state.is(BBTags.Blocks.GROWS_IN_PLAINS)) ci.cancel();
                if (!level.getBiome(pos).is(Tags.Biomes.IS_WET_OVERWORLD) && state.is(BBTags.Blocks.GROWS_IN_WET)) ci.cancel();
            } else if (BasedCommonConfig.SLOWER_CROP_GROWTH.get() && random.nextDouble() <= BasedCommonConfig.FAIL_GROWTH_CHANCE_OVERALL.get()) {
                ci.cancel();
            }
        } else {
            if ((BasedCommonConfig.SLOWER_CROP_GROWTH.get() && random.nextDouble() <= BasedCommonConfig.FAIL_GROWTH_CHANCE_OVERALL.get()) && BasedCommonConfig.SLOWER_CROP_GROWTH_GREENHOUSE.get()) {
                ci.cancel();
            }
        }
    }
}