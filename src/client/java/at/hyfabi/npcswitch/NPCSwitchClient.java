package at.hyfabi.npcswitch;


import at.hyfabi.npcswitch.algorithm.AlgorithmHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.util.logging.Logger;

import static at.hyfabi.npcswitch.MCCommand.registerCommands;



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



