package net.codingarea.cloudbungeecmds.proxy.management;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.wrapper.Wrapper;
import net.codingarea.cloudbungeecmds.ProxyCommandExecuteInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ProxyMessagingManagement {

	public void sendOnEnableMessage() {
		CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_ON_ENABLE,
				new JsonDocument(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_ON_ENABLE_NAME, Wrapper.getInstance().getCurrentServiceInfoSnapshot().getName())
		);
	}

	public void executeCommand(@NotNull String name, UUID uuid, String[] args) {
		CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_EXECUTE_COMMAND,
				new JsonDocument(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_EXECUTE_INFO, new ProxyCommandExecuteInfo(name, uuid, args))
		);
	}

}