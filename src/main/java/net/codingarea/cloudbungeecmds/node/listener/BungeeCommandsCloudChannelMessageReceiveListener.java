package net.codingarea.cloudbungeecmds.node.listener;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.channel.ChannelMessageReceiveEvent;
import net.codingarea.cloudbungeecmds.BungeeCommandExecuteInfo;
import net.codingarea.cloudbungeecmds.BungeeCommandsConstants;
import net.codingarea.cloudbungeecmds.node.BungeeCommandsManagement;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BungeeCommandsCloudChannelMessageReceiveListener {

	private final BungeeCommandsManagement bungeeCommandsManagement = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(BungeeCommandsManagement.class);

	@EventListener
	public void onChannelMessageReceive(ChannelMessageReceiveEvent event) {
		if (!event.getChannel().equals(BungeeCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME) || event.getMessage() == null) return;

		if (event.getMessage().equals(BungeeCommandsConstants.BUNGEE_COMMANDS_EXECUTE_COMMAND)) {

			BungeeCommandExecuteInfo executeInfo = event.getData().get(BungeeCommandsConstants.BUNGEE_COMMANDS_COMMAND_EXECUTE_INFO, BungeeCommandExecuteInfo.class);
			bungeeCommandsManagement.executeCommand(executeInfo.getCommandName(), executeInfo.getUUID(), executeInfo.getArgs());

		} else if (event.getMessage().equals(BungeeCommandsConstants.BUNGEE_COMMANDS_ON_ENABLE)) {
			UUID uuid = event.getData().get(BungeeCommandsConstants.BUNGEE_COMMANDS_COMMAND_ON_ENABLE_UUID, UUID.class);
			bungeeCommandsManagement.registerAllCommandsOnProxy(CloudNetDriver.getInstance().getCloudServiceProvider().getCloudService(uuid));

		}

	}

}