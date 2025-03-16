package com.udu3324.gridfix;

import com.mojang.brigadier.CommandDispatcher;
import com.udu3324.gridfix.commands.Commands;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gridfix implements ClientModInitializer {

	public static Boolean lockXMouse = false;
	public static Boolean lockYMouse = false;

	public static Integer yaw = null;
	public static Integer pitch = null;

	public static final String MOD_ID = "gridfix";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("udu3324 was here!!! gridfix v0"); //todo

		// register the commands
		ClientCommandRegistrationCallback.EVENT.register(Gridfix::registerCommands);
	}

	public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
		Commands.register(dispatcher);
	}
}