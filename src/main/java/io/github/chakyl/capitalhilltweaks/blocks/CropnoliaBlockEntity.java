package io.github.chakyl.capitalhilltweaks.blocks;

import io.github.chakyl.capitalhilltweaks.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.xplat.XplatAbstractions;
import vazkii.botania.client.fx.SparkleParticleData;

import org.jetbrains.annotations.Nullable;

public class CropnoliaBlockEntity extends GeneratingFlowerBlockEntity {
    private static final int RANGE = 3;
    private static final int MAX_MANA = manaForAge(10) * RANGE * RANGE;
    public static final int MANA_BASE = 10;
    public static final int MANA_BASE_GOG = MANA_BASE / 4;

    public CropnoliaBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CROPNOLIA.get(), pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();
        if ( ticksExisted % 100 == 0 && !getLevel().isClientSide) {
            BlockPos flowerPos = this.getBlockPos();
            boolean foundCrop = false;
            for(BlockPos blockpos : BlockPos.betweenClosed(flowerPos.offset(-RANGE, 0, -RANGE), flowerPos.offset(RANGE, 1, RANGE))) {
                BlockState crop = level.getBlockState(blockpos);
                if (crop.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(crop)) {
                    foundCrop = true;
                    int age = cropBlock.getMaxAge();
                    addMana(manaForAge(age));
                    level.setBlock(blockpos, cropBlock.getStateForAge(0), 2);
                    int times = 8 * (int) Math.pow(2, age);
                    for (int j = 0; j < times; ++j) {
                        float f = level.random.nextFloat() * (float) Math.PI * 2.0F;
                        float f1 = level.random.nextFloat() * 0.5F + 0.5F;
                        float f2 = Mth.sin(f) * age * 0.5F * f1;
                        float f3 = Mth.cos(f) * age * 0.5F * f1;
                        float f4 = level.random.nextFloat() * age * 0.5F * f1;
                        level.addParticle(ParticleTypes.ITEM_SLIME, blockpos.getX() + f2, 1 + f4, blockpos.getZ() + f3, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
            if (foundCrop) level.playSound(null, flowerPos, BotaniaSounds.terraBlade, SoundSource.BLOCKS, 1F, 1F);
        }
    }

    @Override
    public @Nullable RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

    private static int manaForAge(int age) {
        return (XplatAbstractions.INSTANCE.gogLoaded() ? MANA_BASE_GOG : MANA_BASE) * (int) Math.pow(2, age);
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

    @Override
    public int getColor() {
        return 0xf9c22b;
    }
}
