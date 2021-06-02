package net.codingarea.cloudbungeecmds.node.listener;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.channel.ChannelMessageReceiveEvent;
import net.codingarea.cloudbungeecmds.ProxyCommandExecuteInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.node.ProxyCommandManagement;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ProxyCommandsCloudChannelMessageReceiveListener {

	private final ProxyCommandManagement proxyCommandManagement = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(ProxyCommandManagement.class);

	@EventListener
	public void onChannelMessageReceive(ChannelMessageReceiveEvent event) {
		if (!event.getChannel().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME) || event.getMessage() == null) return;

		if (event.getMessage().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_EXECUTE_COMMAND)) {

			ProxyCommandExecuteInfo executeInfo = event.getData().get(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_EXECUTE_INFO, ProxyCommandExecuteInfo.class);
			proxyCommandManagement.executeCommand(executeInfo.getCommandName(), executeInfo.getUUID(), executeInfo.getArgs());

		} else if (event.getMessage().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_ON_ENABLE)) {
			UUID uuid = event.getData().get(ProxyCommandsConstants.BUNGEE_COMMANDS_COMMAND_ON_ENABLE_UUID, UUID.class);
			proxyCommandManagement.registerAllCommandsOnProxy(CloudNetDriver.getInstance().getCloudServiceProvider().getCloudService(uuid));

		}

	}

}