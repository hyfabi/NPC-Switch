package at.hyfabi.npcswitch;

import at.hyfabi.npcswitch.algorithm.AlgorithmHandler;
import at.hyfabi.npcswitch.algorithm.Command;
import at.hyfabi.npcswitch.algorithm.DigSquare;
import at.hyfabi.npcswitch.algorithm.LookAt;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;


public class MCCommand {

    public static ArrayList<BlockPos> positions = new ArrayList<>();

    public static boolean test = false;

    public static void registerCommands(){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal("pos").executes(MCCommand::savePos));
            dispatcher.register(literal("dig").executes(MCCommand::executeDig));
            dispatcher.register(literal("start").executes(MCCommand::startAlg));
            dispatcher.register(literal("look").executes(context -> {
                new LookAt(positions.get(positions.size()-1), context.getSource().getClient() );
                return 0;
            }));
            dispatcher.register(literal("cam").executes(context -> {
                test = !test;
                return 0;
            }));
            dispatcher.register(literal("cl").executes(context -> {
                positions.clear();
                return 0;
            }));
            dispatcher.register(literal("yaw")
                    .then(argument("yaw", FloatArgumentType.floatArg())
                    .executes(context -> {
                        Objects.requireNonNull(context.getSource().getClient().player).setYaw(FloatArgumentType.getFloat(context, "yaw"));
                    return 0;
            })));
            dispatcher.register(literal("pitch")
                    .then(argument("pitch", FloatArgumentType.floatArg())
                            .executes(context -> {
                                Objects.requireNonNull(context.getSource().getClient().player).setPitch(FloatArgumentType.getFloat(context, "pitch"));
                                return 0;
                            })));
            dispatcher.register(literal("cam").executes(context -> {
                test = !test;
                return 0;
            }));
        });
    }

    private static int startAlg(CommandContext<FabricClientCommandSource> context) {
        AlgorithmHandler.SINGLETON.algorithmState = AlgorithmHandler.AlgorithmState.AUTOMATIC;
        return 0;
    }

    public static int executeDig(CommandContext<FabricClientCommandSource> command){
        if(positions.size() < 2){
            command.getSource().sendError(Text.literal("2 positions dude"));
            return 1;
        }

        Command command1 = new DigSquare(positions.get(0), positions.get(1), command.getSource().getClient());

        AlgorithmHandler.SINGLETON.queue.add(command1);
        return 0;
    }

    public static int savePos(CommandContext<FabricClientCommandSource> command){
        BlockPos posI =  command.getSource().getPlayer().getBlockPos();
        positions.add(posI);

        command.getSource().sendFeedback(Text.literal("Saved Position:" + posI));
        return 0;
    }

    public static Vec3i castToInt(Vec3d vec3d){
        return new Vec3i((int)(vec3d.x), (int)(vec3d.y), (int)(vec3d.z));
    }

}
