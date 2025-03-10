package io.github.chakyl.croptania.flowers;

import net.minecraft.world.level.block.entity.BlockEntityType;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;

import java.util.function.Supplier;

public class CropnoliaFloatingFlowerBlock extends FloatingSpecialFlowerBlock  {
    public CropnoliaFloatingFlowerBlock(Properties props, Supplier<BlockEntityType<? extends SpecialFlowerBlockEntity>> blockEntityType) {
        super(props, blockEntityType);
    }
}
