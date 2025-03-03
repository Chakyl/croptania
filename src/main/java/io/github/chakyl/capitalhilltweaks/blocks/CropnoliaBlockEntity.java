package io.github.chakyl.capitalhilltweaks.blocks;

import io.github.chakyl.capitalhilltweaks.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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
        if ( ticksExisted % 100 == 0 ) {
            BlockPos flowerPos = this.getBlockPos();
            boolean foundCrop = false;
            for(BlockPos blockpos : BlockPos.betweenClosed(flowerPos.offset(-RANGE, 0, -RANGE), flowerPos.offset(RANGE, 1, RANGE))) {
                BlockState crop = level.getBlockState(blockpos);
                if (crop.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(crop)) {
                    foundCrop = true;
                    int age = cropBlock.getMaxAge();
                    addMana(manaForAge(age));
                    level.setBlock(blockpos, cropBlock.getStateForAge(0), 2);
                    for (int i = 0; i < 20; ++i) {
                        Vec3 offset = crop.getOffset(level, blockpos);
                        double x = blockpos.getX() + offset.x;
                        double y = blockpos.getY() + offset.y;
                        double z = blockpos.getZ() + offset.z;

                        SparkleParticleData data = SparkleParticleData.sparkle(level.random.nextFloat(), 0, 170, 0, 5);

                        level.addParticle(data, x + Mth.randomBetween(level.random, 0, 0.9f), y + Mth.randomBetween(level.random, 0.2f, 0.4f), z + Mth.randomBetween(level.random, 0, 0.9f), 0, 0, 0);
                    }
                    break;
                }
            }
            if (foundCrop) level.playSound(null, flowerPos, BotaniaSounds.terraBlade, SoundSource.BLOCKS, 1F, 1F);
            sync();
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
