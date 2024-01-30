package at.hyfabi.npcswitch.mixin.client;

import at.hyfabi.npcswitch.algorithm.AlgorithmHandler;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(at = @At("HEAD"), method = "onKey")
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci ){
        if(scancode == 36 ||  AlgorithmHandler.SINGLETON.algorithmState == AlgorithmHandler.AlgorithmState.MANUAL)
            return;
        AlgorithmHandler.SINGLETON.algorithmState = AlgorithmHandler.AlgorithmState.MANUAL;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("Stopped due to manual input"));
    }

}
