package at.hyfabi.npcswitch;


import at.hyfabi.npcswitch.algorithm.AlgorithmHandler;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Objects;
import java.util.logging.Logger;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
// word()
import static com.mojang.brigadier.arguments.StringArgumentType.word;
// literal("foo")
import static net.minecraft.server.command.CommandManager.literal;
// argument("bar", word())
import static net.minecraft.server.command.CommandManager.argument;
// Import everything in the CommandManager
import static net.minecraft.server.command.CommandManager.*;



public class NPCSwitchClient implements ClientModInitializer {

	public static final String MOD_ID = "npcswitch";
	public static final Logger MOD_LOGGER = Logger.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {

		MOD_LOGGER.info("Starting");

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			AlgorithmHandler.SINGLETON.tick(client);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("mul")

				.executes(context -> executeMultiply(IntegerArgumentType.getInteger(context, "value"), IntegerArgumentType.getInteger(context, "value"), context))

				.executes(context -> executeMultiply(IntegerArgumentType.getInteger(context, "value"), IntegerArgumentType.getInteger(context, "value2"), context))));

		}


	private static int executeMultiply(int value, int value2, CommandContext<ServerCommandSource> context) {
		final int result = value * value2;
		context.getSource().sendFeedback(() -> Text.literal("%s × %s = %s".formatted(value, value2, result)), false);
		return result;
	}

}



