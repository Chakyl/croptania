package io.github.chakyl.croptania.client;

import io.github.chakyl.croptania.registry.BlockEntityRegistry;
import io.github.chakyl.croptania.registry.BlockRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import vazkii.botania.client.render.block_entity.SpecialFlowerBlockEntityRenderer;

import java.util.function.BiConsumer;

public class ClientRegistry {
    static void registerLayers(FMLClientSetupEvent event) {
        registerRenderTypes(ItemBlockRenderTypes::setRenderLayer);
    }
    public static void registerRenderTypes(BiConsumer<Block, RenderType> r) {
        RenderType cutout = RenderType.cutout();
        r.accept(BlockRegistry.cropnolia.get(), cutout);
        r.accept(BlockRegistry.cropnoliaFloating.get(), cutout);
    }
    static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.CROPNOLIA.get(), SpecialFlowerBlockEntityRenderer::new);
    }
}
