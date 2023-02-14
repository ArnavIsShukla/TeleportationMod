package net.kaupenjoe.tutorialmod.item.advanced;

import com.mojang.blaze3d.platform.InputConstants;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

public class AdvancedMagicalStaff extends Item {
    public AdvancedMagicalStaff(Properties properties) {
        super(properties);
    }
    @Override
    public boolean isRepairable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack tool, ItemStack material) {
        return material.getItem() ==  ModItems.MAGICAL_SHARD.get();
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(Screen.hasShiftDown()){
            //teleportation
            BlockHitResult ray = rayTrace(world, player, ClipContext.Fluid.NONE);
            player.getCooldowns().addCooldown(this, 60);
            BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());
            player.setPos(lookPos.getX(), lookPos.getY(), lookPos.getZ());
            //durability
            stack.setDamageValue(stack.getDamageValue() + 1);
            if (stack.getDamageValue() >= stack.getMaxDamage()){
                stack.setCount(0);
            }
            //effects
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
            //sounds
            world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.5F);
        }
        return super.use(world, player, hand);
    }
    protected static BlockHitResult rayTrace(Level world, Player player, ClipContext.Fluid fluidMode) {
        double range = 15;
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vector3d = player.getEyePosition(1.0F);
        float f2 = (float)Math.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = (float)Math.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = (float)-Math.cos(-f * ((float)Math.PI / 180F));
        float f5 = (float)Math.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vector3d1 = vector3d.add((double)f6 * range, (double)f5 * range, (double)f7 * range);
        return world.clip(new ClipContext(vector3d, vector3d1, ClipContext.Block.OUTLINE, fluidMode, player));
    }
}
