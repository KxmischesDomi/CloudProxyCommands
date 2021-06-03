package net.codingarea.cloudbungeecmds.cloud.cloudnet3.listener;

import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.channel.ChannelMessageReceiveEvent;
import net.codingarea.cloudbungeecmds.ProxyCommandExecuteInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.cloud.ProxyCommandsAPI;
import net.codingarea.cloudbungeecmds.management.ProxyCommandManagement;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CloudNet3ChannelMessageReceiveListener {

	@EventListener
	public void onChannelMessageReceive(ChannelMessageReceiveEvent event) {
		if (!event.getChannel().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME) || event.getMessage() == null) return;

		ProxyCommandManagement commandsManagement = ProxyCommandsAPI.getCommandsManagement();

		if (event.getMessage().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_EXECUTE_COMMAND)) {

			ProxyCommandExecuteInfo executeInfo = event.getData().get(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_EXECUTE_INFO, ProxyCommandExecuteInfo.class);
			commandsManagement.executeCommand(executeInfo.getCommandName(), executeInfo.getUUID(), executeInfo.getArgs());

		} else if (event.getMessage().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_ON_ENABLE)) {
			String serviceName = event.getData().getString(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_ON_ENABLE_NAME);
			ProxyCommandsAPI.getModule().registerAllCommandsOnProxy(serviceName);
		}

	}

}