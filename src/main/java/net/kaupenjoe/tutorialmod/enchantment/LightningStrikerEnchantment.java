package net.kaupenjoe.tutorialmod.enchantment;

import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LightningStrikerEnchantment extends Enchantment {
    protected LightningStrikerEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
        super(rarity, category, slots);
    }

    @Override

    public void doPostAttack(LivingEntity attacker, Entity target, int level) {
        if(!attacker.level.isClientSide()){
            ServerLevel world = (ServerLevel) attacker.level;
            ServerPlayer player = ((ServerPlayer) attacker);
            BlockPos pos = target.blockPosition();

            if(level == 1){
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, pos,
                MobSpawnType.TRIGGERED, true, true);
            }
            else if(level == 2){
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, pos,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, pos,
                        MobSpawnType.TRIGGERED, true, true);
            }
            else if(level == 3){
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, pos,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, pos,
                        MobSpawnType.TRIGGERED, true, true);
                EntityType.LIGHTNING_BOLT.spawn(world, null, player, pos,
                        MobSpawnType.TRIGGERED, true, true);
            }
        }
        super.doPostAttack(attacker, target, level);
    }


    @Override
    public int getMaxLevel() {
        return 3;
    }
}
