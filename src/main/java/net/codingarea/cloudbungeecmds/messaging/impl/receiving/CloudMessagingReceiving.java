package net.codingarea.cloudbungeecmds.messaging.impl.receiving;

import net.codingarea.cloudbungeecmds.utils.ProxyCommandExecuteInfo;
import net.codingarea.cloudbungeecmds.utils.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.messaging.MessageDocument;
import net.codingarea.cloudbungeecmds.messaging.MessagingHandler;
import net.codingarea.cloudbungeecmds.messaging.MessagingReceiver;
import net.codingarea.cloudbungeecmds.module.ProxyCommandsAPI;
import net.codingarea.cloudbungeecmds.module.management.ProxyCommandManagement;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CloudMessagingReceiving implements MessagingReceiver {

	@Override
	public void handleIncomingMessage(String channel, String message, MessageDocument data) {
		if (!channel.equals(ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME) || message == null) return;

		ProxyCommandManagement commandsManagement = ProxyCommandsAPI.getCommandsManagement();

		if (message.equals(ProxyCommandsConstants.BUNGEE_COMMANDS_EXECUTE_COMMAND)) {

			ProxyCommandExecuteInfo executeInfo = data.get(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_EXECUTE_INFO, ProxyCommandExecuteInfo.class);
			commandsManagement.executeCommand(executeInfo.getCommandName(), executeInfo.getUUID(), executeInfo.getArgs());

		} else if (message.equals(ProxyCommandsConstants.BUNGEE_COMMANDS_ON_ENABLE)) {
			String serviceName = data.getString(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_ON_ENABLE_NAME);

			for (ProxyCommandInfo commandInfo : ProxyCommandsAPI.getCommandsManagement().getBungeeCommandExecutors().keySet()) {
				MessagingHandler.getInstance().sendMessageToService(
						ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
						ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND,
						MessagingHandler.getInstance().createDocument().set(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND_INFO, commandInfo),
						serviceName
				);
			}
		}
	}

}