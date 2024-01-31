package at.hyfabi.npcswitch.algorithm;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class LookAt implements Command{

    ClientPlayerEntity clientPlayerEntity;

    BlockPos blockPos;

    public LookAt(BlockPos blockToLook, MinecraftClient minecraftClient){
        blockPos = blockToLook;
        clientPlayerEntity = Objects.requireNonNull(minecraftClient.player);

        Vec3d pP = clientPlayerEntity.getPos();

        Vec3d relP = Vec3d.of(blockPos).subtract(pP);

        float yaw = (float) Math.toDegrees(Math.atan(relP.y / relP.x));

        clientPlayerEntity.setYaw(yaw);
    }

    public LookAt(float yaw, float pitch, MinecraftClient minecraftClient){
        assert minecraftClient.player != null;
        if(yaw < 1 && yaw >= 0)
            minecraftClient.player.setYaw(yaw);
        if(pitch < 1 && pitch >= 0)
            minecraftClient.player.setPitch(pitch);
    }

    @Override
    public void execute(MinecraftClient minecraftClient) {


    }
}
