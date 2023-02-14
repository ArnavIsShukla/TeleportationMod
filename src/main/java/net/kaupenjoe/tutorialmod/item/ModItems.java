package net.kaupenjoe.tutorialmod.item;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.item.advanced.AdvancedMagicalStaff;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);
    public static final RegistryObject<Item> MAGICAL_CRYSTAL = ITEMS.register("magical_crystal",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MAGICAL_SHARD = ITEMS.register("magical_shard",
            () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MAGICAL_STAFF = ITEMS.register("magical_staff",
            () -> new AdvancedMagicalStaff(new Item.Properties().defaultDurability(100)));

    public static final RegistryObject<Item> POTTY = ITEMS.register("potty",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                    .nutrition(1)
                    .saturationMod(0.2f)
                    .alwaysEat()
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200, 0), 1)
                    .build())));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
