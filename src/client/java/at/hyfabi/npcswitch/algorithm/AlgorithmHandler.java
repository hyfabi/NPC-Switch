package at.hyfabi.npcswitch.algorithm;

import net.minecraft.client.MinecraftClient;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class AlgorithmHandler {

    public static AlgorithmHandler SINGLETON = new AlgorithmHandler();
    public AlgorithmState algorithmState = AlgorithmState.MANUAL;
    public ArrayDeque<Command> queue = new ArrayDeque<>();

    public void tick(MinecraftClient minecraftClient){
        if(algorithmState == AlgorithmState.MANUAL)
            return;

        if(queue.peek() != null)
            queue.peek().execute(minecraftClient);
    }

    public enum AlgorithmState{
        MANUAL, AUTOMATIC;
    }

}
