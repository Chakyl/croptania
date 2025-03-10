package io.github.chakyl.croptania;

import com.mojang.logging.LogUtils;
import io.github.chakyl.croptania.client.ClientRegistryHandler;
import io.github.chakyl.croptania.registry.BlockEntityRegistry;
import io.github.chakyl.croptania.registry.BlockRegistry;
import io.github.chakyl.croptania.registry.CreativeTab;
import io.github.chakyl.croptania.registry.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Croptania.MODID)
public class Croptania {
    public static final String MODID = "croptania";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Croptania() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemRegistry.ITEMS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITY_TYPE.register(modEventBus);
        CreativeTab.TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientRegistryHandler::init);
    }
}