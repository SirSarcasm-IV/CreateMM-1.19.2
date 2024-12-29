package net.sirsarcasm.createmm.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.sirsarcasm.createmm.CreateMM;
import net.sirsarcasm.createmm.networking.ModMessages;
import net.sirsarcasm.createmm.networking.packet.JetLaunchC2SPacket;
import net.sirsarcasm.createmm.util.KeyBinding;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = CreateMM.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.JET_LAUNCH.consumeClick()) {
                ModMessages.sendToServer(new JetLaunchC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = CreateMM.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.JET_LAUNCH);

        }
    }


}
