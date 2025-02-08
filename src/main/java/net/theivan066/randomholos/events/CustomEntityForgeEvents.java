package net.theivan066.randomholos.events;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.theivan066.randomholos.RandomHolos;
import net.theivan066.randomholos.entity.custom.MikoEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@EventBusSubscriber(modid = RandomHolos.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CustomEntityForgeEvents {
    //miko あえんびえん death
    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof MikoEntity) {
            DamageSource damageSource = event.getSource();
            Entity miko = event.getEntity();
            boolean fireEnchants = false;
            if (damageSource.is(DamageTypes.PLAYER_ATTACK)) {
                Player player = (Player) damageSource.getEntity();
                ItemStack mainHandItem = Objects.requireNonNull(player).getMainHandItem();

                Optional<Holder.Reference<Enchantment>> fireAspect = player.level().holder(Enchantments.FIRE_ASPECT);
                Optional<Holder.Reference<Enchantment>> flame = player.level().holder(Enchantments.FLAME);

                int fireAspectLevel = fireAspect.isPresent() ? mainHandItem.getEnchantmentLevel(fireAspect.get()) : 0;
                int flameLevel = flame.isPresent() ? mainHandItem.getEnchantmentLevel(flame.get()) : 0;
                
                fireEnchants = fireAspectLevel > 0 || flameLevel > 0;
            }
            boolean diedFromAenbien = damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.IN_FIRE) || damageSource.is(DamageTypes.LAVA) || damageSource.is(DamageTypes.FIREBALL) || damageSource.is(DamageTypes.PLAYER_EXPLOSION) || miko.isOnFire();
            if (fireEnchants && !miko.level().isClientSide) {
                Player player = (Player) damageSource.getEntity();
                player.sendSystemMessage(Component.translatable("messages.randomholos.aenbien"));
                miko.playSound(SoundEvents.ENDERMAN_DEATH, 1, 1);
            } else if (diedFromAenbien) {
                List<? extends Player> players = miko.level().players();
                for (Player player : players) {
                    if (!miko.level().isClientSide) {
                        player.sendSystemMessage(Component.translatable("messages.randomholos.aenbien"));
                    }
                    miko.playSound(SoundEvents.ENDERMAN_DEATH, 1, 1);
                }
            }
        }
    }
}

