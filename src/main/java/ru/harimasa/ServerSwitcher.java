package ru.harimasa;

/*
 * Copyright (c) 2025 by harimasa.
 */

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerSwitcher implements ModInitializer {
	public static final String MOD_ID = "server-switcher";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("hi");
	}
}