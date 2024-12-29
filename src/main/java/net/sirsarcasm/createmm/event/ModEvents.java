package net.sirsarcasm.createmm.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.sirsarcasm.createmm.CreateMM;
import net.sirsarcasm.createmm.charge.JetpackCharge;
import net.sirsarcasm.createmm.charge.JetpackChargeProvider;

import static com.simibubi.create.content.equipment.armor.BacktankUtil.consumeAir;

@Mod.EventBusSubscriber(modid = CreateMM.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(JetpackChargeProvider.JETPACK_CHARGE).isPresent()) {
                event.addCapability(new ResourceLocation(CreateMM.MOD_ID, "properties"), new JetpackChargeProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(JetpackChargeProvider.JETPACK_CHARGE).ifPresent(oldStore -> {
                event.getOriginal().getCapability(JetpackChargeProvider.JETPACK_CHARGE).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(JetpackCharge.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(JetpackChargeProvider.JETPACK_CHARGE).ifPresent(charge -> {
                if((charge.getCharge() <= 1190) && event.player.isOnGround()) {
                    charge.addCharge(10); // 1 charge per 2 seconds, 10 per tick
                    event.player.sendSystemMessage(Component.literal("Current Charge " + charge.getCharge()));
                }
            });
        }
    }
}
