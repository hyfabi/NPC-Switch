package at.hyfabi.npcswitch.algorithm;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.joml.Vector3i;

import java.util.ArrayList;

public class DigSquare implements Command{

    final ClientWorld clientWorld;
    final ClientPlayerEntity clientPlayerEntity;

    final BlockPos pos1, pos2;

    final ArrayList<BlockPos> buffer = new ArrayList<>();

    public DigSquare(BlockPos pos1, BlockPos pos2, MinecraftClient minecraftClient){
        clientWorld = minecraftClient.world;
        clientPlayerEntity = minecraftClient.player;

        this.pos1 = pos1;
        this.pos2 = pos2;
        calc(pos1, pos2);
    }

    private void calc(BlockPos pos1, BlockPos pos2) {



    }

    @Override
    public void execute(MinecraftClient minecraftClient) {

    }

    public void goTo(BlockPos blockPos){

    }


}
