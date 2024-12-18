package net.sirsarcasm.createmm.networking.packet;

import com.google.common.graph.Network;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.sirsarcasm.createmm.charge.JetpackCharge;
import net.sirsarcasm.createmm.charge.JetpackChargeProvider;
import net.sirsarcasm.createmm.item.JetpackItem;
import net.sirsarcasm.createmm.util.KeyBinding;

import javax.swing.text.html.parser.Entity;
import java.util.function.Supplier;


public class ExampleC2SPacket {

    private static JetpackCharge charge = new JetpackCharge();

    public ExampleC2SPacket() {

    }

    public ExampleC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // THIS IS THE SERVER
            Player player = Minecraft.getInstance().player;

            if (KeyBinding.JET_LAUNCH.isDown()) {
                if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JetpackItem && charge.getCharge() >= 400) {
                    Vec3 lookVec = player.getLookAngle();
                    player.setDeltaMovement(lookVec.scale(1.5)); // Adjust the scale factor for desired launch strength
                    player.hurtMarked = true; // Ensures the player velocity is updated on the client#
                    charge.subCharge(400);
                }
            }
        });
        return true;
    }

    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = Minecraft.getInstance().player;
        if (KeyBinding.JET_LAUNCH.isDown()) {
            if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JetpackItem && charge.getCharge() >= 400) {
                Vec3 lookVec = player.getLookAngle();
                player.setDeltaMovement(lookVec.scale(1.5)); // Adjust the scale factor for desired launch strength
                player.hurtMarked = true; // Ensures the player velocity is updated on the client#
                charge.subCharge(400);
            }
        }
    }

    public static void onKeyInput(TickEvent.ClientTickEvent event) {
        if (KeyBinding.JET_LAUNCH.isDown()) {
            Player player = Minecraft.getInstance().player;
            if (player != null && player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof JetpackItem && charge.getCharge() >= 400) {
                Vec3 lookVec = player.getLookAngle();
                player.setDeltaMovement(lookVec.scale(1.5)); // Adjust the scale factor for desired launch strength
                player.hurtMarked = true; // Ensures the player velocity is updated on the client#
                charge.subCharge(400);
            }
        }
    }
}
