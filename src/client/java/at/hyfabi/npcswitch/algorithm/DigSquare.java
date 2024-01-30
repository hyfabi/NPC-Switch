package at.hyfabi.npcswitch.algorithm;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DigSquare implements Command{

    final ClientWorld clientWorld;
    final ClientPlayerEntity clientPlayerEntity;

    final BlockPos pos1, pos2;

    ArrayDeque<BlockPos> buffer;

    public DigSquare(BlockPos pos1, BlockPos pos2, MinecraftClient minecraftClient){
        clientWorld = minecraftClient.world;
        clientPlayerEntity = minecraftClient.player;

        this.pos1 = pos1;
        this.pos2 = pos2;
        buffer = new ArrayDeque<>(calc(pos1, pos2));
    }

    private List<BlockPos> calc(BlockPos pos1, BlockPos pos2) {

        int side1 = Math.abs(pos1.getX() - pos2.getX()), side2 = Math.abs(pos1.getX() - pos2.getX());

        ArrayList<BlockPos> blockPositions = new ArrayList<>();
        for(int y = clientWorld.getTopY(); y > clientWorld.getBottomY(); y--)
            for (int x = 0; x <= side1; x++)
                for (int z = 0; z <= side2; z++)
                    blockPositions.add(new BlockPos(x, y, z));

        return blockPositions.stream().filter(this::canBeMined).collect(Collectors.toList());
    }

    public boolean canBeMined(BlockPos blockPos){
        Block block = clientWorld.getBlockState(blockPos).getBlock();

        if (block.equals(Blocks.AIR) || block.equals(Blocks.BEDROCK)) {
            return false;
        }

        return true;
    }

    @Override
    public void execute(MinecraftClient minecraftClient) {
        BlockPos pos = buffer.peekFirst();

        if(clientPlayerEntity.getPos().isInRange(new Vec3d(pos.getX(), pos.getY(), pos.getZ()), 5)){

        }else{
            lookAt(pos);
        }

    }

    public void lookAt(BlockPos blockPos){
        
    }

    public void goTo(BlockPos blockPos){

    }


}
