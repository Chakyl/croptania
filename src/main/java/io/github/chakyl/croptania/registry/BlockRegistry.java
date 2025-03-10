package io.github.chakyl.croptania.registry;

import io.github.chakyl.croptania.Croptania;
import io.github.chakyl.croptania.flowers.CropnoliaFloatingFlowerBlock;
import io.github.chakyl.croptania.flowers.CropnoliaFlowerBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import vazkii.botania.common.block.BotaniaBlocks;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Croptania.MODID);
    public static final RegistryObject<CropnoliaFlowerBlock> cropnolia = BLOCKS.register("cropnolia", () -> new CropnoliaFlowerBlock(MobEffects.REGENERATION, 60, BlockBehaviour.Properties.copy(Blocks.POPPY), BlockEntityRegistry.CROPNOLIA::get));
    public static final RegistryObject<CropnoliaFloatingFlowerBlock> cropnoliaFloating = BLOCKS.register("floating_cropnolia", () -> new CropnoliaFloatingFlowerBlock(BotaniaBlocks.FLOATING_PROPS, BlockEntityRegistry.CROPNOLIA::get));
}