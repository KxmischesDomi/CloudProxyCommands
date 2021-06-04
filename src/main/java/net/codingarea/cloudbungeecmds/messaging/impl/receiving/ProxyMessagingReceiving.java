package net.codingarea.cloudbungeecmds.messaging.impl.receiving;

import net.codingarea.cloudbungeecmds.utils.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.messaging.MessageDocument;
import net.codingarea.cloudbungeecmds.messaging.MessagingReceiver;
import net.codingarea.cloudbungeecmds.proxy.ProxyCommandsPlugin;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ProxyMessagingReceiving implements MessagingReceiver {

	@Override
	public void handleIncomingMessage(String channel, String message, MessageDocument data) {
		if (!channel.equals(ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME)) return;
		if (message == null) return;
		if (message.equals(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND)) {

			ProxyCommandInfo commandInfo = data.get(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND_INFO, ProxyCommandInfo.class);
			ProxyCommandsPlugin.getInstance().registerCommand(commandInfo);

		} else if (message.equals(ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND)) {
			ProxyCommandsPlugin.getInstance().unregisterCommand(data.getString(ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND_NAME));
		}
	}
	
}