package at.hyfabi.npcswitch.mixin.client;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static at.hyfabi.npcswitch.NPCSwitchClient.MOD_LOGGER;

@Mixin(ClientPlayerEntity.class)
public class PlayerTicker {

    @Inject(at = @At("RETURN"), method = "<init>*")
    public void onConstructed(CallbackInfo ci) {
        MOD_LOGGER.info("Client Injected");
    }
}
