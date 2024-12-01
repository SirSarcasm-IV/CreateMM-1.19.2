package net.sirsarcasm.createmm.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String CATEGORY = "key.categories.cmm_keys";
    public static final KeyMapping JET_LAUNCH = new KeyMapping("key.jetpack.launch", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, CATEGORY);
}
