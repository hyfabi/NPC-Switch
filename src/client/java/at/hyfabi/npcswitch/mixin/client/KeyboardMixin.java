package at.hyfabi.npcswitch.mixin.client;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(at = @At("HEAD"), method = "onKey")
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci ){
        if(key == GLFW.GLFW_KEY_1)
            return;

        long l = MinecraftClient.getInstance().getWindow().getHandle();

        MinecraftClient.getInstance().keyboard.onKey(l, GLFW.GLFW_KEY_1, GLFW.glfwGetKeyScancode(GLFW.GLFW_KEY_1), GLFW.GLFW_PRESS, 0);
    }

}
