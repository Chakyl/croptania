package io.github.chakyl.capitalhilltweaks.registry;

import io.github.chakyl.capitalhilltweaks.Croptania;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Croptania.MODID);

    public static final RegistryObject<BlockItem> floating_cropnolia_ITEM = ITEMS.register("floating_cropnolia", () -> new BlockItem(BlockRegistry.cropnoliaFloating.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> CROPNOLIA_ITEM = ITEMS.register("cropnolia", () -> new BlockItem(BlockRegistry.cropnolia.get(), new Item.Properties()));

}
