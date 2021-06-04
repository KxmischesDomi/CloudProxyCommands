package net.codingarea.cloudbungeecmds.proxy.management;

import net.codingarea.cloudbungeecmds.utils.ProxyCommandExecuteInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.messaging.MessagingHandler;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ProxyMessagingManagement {

	public void sendOnEnableMessage() {
		MessagingHandler.getInstance().sendMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_ON_ENABLE,
				MessagingHandler.getInstance().createDocument().set(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_ON_ENABLE_NAME, MessagingHandler.getInstance().getCurrentServiceName())
		);
	}

	public void executeCommand(@NotNull String name, UUID uuid, String[] args) {
		MessagingHandler.getInstance().sendMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_EXECUTE_COMMAND,
				MessagingHandler.getInstance().createDocument().set(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_EXECUTE_INFO, new ProxyCommandExecuteInfo(name, uuid, args))
		);
	}

}