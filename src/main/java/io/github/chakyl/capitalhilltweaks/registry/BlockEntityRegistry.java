package io.github.chakyl.capitalhilltweaks.registry;

import io.github.chakyl.capitalhilltweaks.Croptania;
import io.github.chakyl.capitalhilltweaks.blocks.CropnoliaBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Croptania.MODID);


    public static final RegistryObject<BlockEntityType<CropnoliaBlockEntity>> CROPNOLIA = BLOCK_ENTITY_TYPE.register("cropnolia", () -> create(CropnoliaBlockEntity::new, BlockRegistry.cropnoliaFloating.get(), BlockRegistry.cropnolia.get()));


    private static <T extends BlockEntity> BlockEntityType<T> create(BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
        return BlockEntityType.Builder.of(factory, blocks).build(null);
    }
}