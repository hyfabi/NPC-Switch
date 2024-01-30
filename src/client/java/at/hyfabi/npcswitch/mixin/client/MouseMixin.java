package at.hyfabi.npcswitch.mixin.client;

import at.hyfabi.npcswitch.algorithm.AlgorithmHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MouseMixin {

    @Shadow protected abstract void onCursorPos(long window, double x, double y);

    @Inject(at = @At("HEAD"), method = "onMouseButton")
    public void onKey(long window, int button, int action, int mods, CallbackInfo ci){
        if(1 == 1)
            return;

        if(AlgorithmHandler.SINGLETON.algorithmState == AlgorithmHandler.AlgorithmState.MANUAL)
            return;
        AlgorithmHandler.SINGLETON.algorithmState = AlgorithmHandler.AlgorithmState.MANUAL;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("Stopped due to manual input"));
    }

//    @Inject(at = @At("HEAD"), method = "onCursorPos", cancellable = true    )
//    public void cursorPos(long window, double x, double y, CallbackInfo ci){
//
//    }
}
