package io.github.chakyl.croptania.registry;

import io.github.chakyl.croptania.Croptania;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Croptania.MODID);

    public static final RegistryObject<CreativeModeTab> CROPTANIA_CREATIVE_TAB = TABS.register("croptania_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.CROPNOLIA_ITEM.get()))
                    .title(Component.translatable("itemGroup.croptania"))
                    .displayItems((parameters, output) -> {
                        output.accept(ItemRegistry.CROPNOLIA_ITEM.get());
                        output.accept(ItemRegistry.floating_cropnolia_ITEM.get());
                    }).build());

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
    }
}