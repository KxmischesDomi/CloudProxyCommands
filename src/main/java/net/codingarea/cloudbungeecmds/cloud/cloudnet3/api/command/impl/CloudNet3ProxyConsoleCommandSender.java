package net.codingarea.cloudbungeecmds.cloud.cloudnet3.api.command.impl;

import de.dytanic.cloudnet.common.logging.LogLevel;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import net.codingarea.cloudbungeecmds.cloud.api.command.impl.ProxyConsoleCommandSender;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CloudNet3ProxyConsoleCommandSender implements ProxyConsoleCommandSender {

	@Override
	public void sendMessage(String message) {
		CloudNetDriver.getInstance().getLogger().log(LogLevel.COMMAND, message);
	}

}