package net.sirsarcasm.createmm.networking.packet;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.network.NetworkEvent;
import net.sirsarcasm.createmm.charge.JetpackCharge;
import net.sirsarcasm.createmm.charge.JetpackChargeProvider;
import net.sirsarcasm.createmm.item.JetpackItem;
import net.sirsarcasm.createmm.util.KeyBinding;

import java.util.function.Supplier;


public class JetLaunchC2SPacket {

    private static final String MESSAGE_NO_CHARGE = "message.tutorialmod.no_charge";

    public JetLaunchC2SPacket() {

    }

    public JetLaunchC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // THIS IS THE SERVER
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            player.getCapability(JetpackChargeProvider.JETPACK_CHARGE).ifPresent(charge -> {
                if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JetpackItem && charge.getCharge() >= 400) {
                        charge.subCharge(400);
                        player.sendSystemMessage(Component.literal("Current Charge " + charge.getCharge()));
                        Vec3 lookVec = player.getLookAngle();
                        player.setDeltaMovement(lookVec.scale(2)); // Adjust the scale factor for desired launch strength
                        player.hurtMarked = true;
                } else {
                        player.sendSystemMessage(Component.translatable(MESSAGE_NO_CHARGE).withStyle(ChatFormatting.RED));
                        player.sendSystemMessage(Component.literal("Current Charge " + charge.getCharge()));
                }
            });
        });
        return true;

    }


}
