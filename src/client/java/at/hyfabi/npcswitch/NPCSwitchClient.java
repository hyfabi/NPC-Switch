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

import static at.hyfabi.npcswitch.MCCommand.registerCommands;
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
		registerCommands();

	}
}



