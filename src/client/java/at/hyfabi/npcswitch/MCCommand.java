package at.hyfabi.npcswitch;

import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import java.util.ArrayList;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;


public class MCCommand {

    public static ArrayList<Vec3i> positions = new ArrayList<>();

    public static void registerCommands(){
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(literal("pos")
                .executes(MCCommand::savePos)));
    }

    public static int savePos(CommandContext<FabricClientCommandSource> command){
        Vec3i posI =  castToInt(command.getSource().getPosition());
        positions.add(posI);

        command.getSource().sendFeedback(Text.literal("Saved Position:" + posI));
        return 0;
    }

    public static Vec3i castToInt(Vec3d vec3d){
        return new Vec3i((int)(vec3d.x), (int)(vec3d.y), (int)(vec3d.z));
    }

}
