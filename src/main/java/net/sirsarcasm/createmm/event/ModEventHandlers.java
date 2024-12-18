package net.sirsarcasm.createmm.event;

import net.sirsarcasm.createmm.charge.JetpackCharge;
import net.sirsarcasm.createmm.charge.JetpackChargeProvider;
import net.sirsarcasm.createmm.util.KeyBinding;
import net.sirsarcasm.createmm.item.JetpackItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "create_mm", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandlers {

    private static JetpackCharge charge = new JetpackCharge();




}
