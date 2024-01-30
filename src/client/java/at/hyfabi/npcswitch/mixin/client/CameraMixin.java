package at.hyfabi.npcswitch.mixin.client;

import at.hyfabi.npcswitch.MCCommand;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {

    @Inject(at = @At("HEAD"), method = "updateEyeHeight", cancellable = true)
    public void updateEyeHeight(CallbackInfo ci){
        if(MCCommand.test)
            ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "update", cancellable = true)
    public void update(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci){
        if(MCCommand.test)
            ci.cancel();
    }

}
