package net.codingarea.cloudbungeecmds.cloud.cloudnet3;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.event.IEventManager;
import de.dytanic.cloudnet.driver.module.ModuleLifeCycle;
import de.dytanic.cloudnet.driver.module.ModuleTask;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.module.NodeCloudNetModule;
import net.codingarea.cloudbungeecmds.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.cloud.ProxyCommandsAPI;
import net.codingarea.cloudbungeecmds.cloud.api.command.impl.ProxyConsoleCommandSender;
import net.codingarea.cloudbungeecmds.cloud.api.command.impl.ProxyPlayerCommandSender;
import net.codingarea.cloudbungeecmds.cloud.cloudnet3.api.command.impl.CloudNet3ProxyConsoleCommandSender;
import net.codingarea.cloudbungeecmds.cloud.cloudnet3.api.command.impl.CloudNet3ProxyPlayerCommandSender;
import net.codingarea.cloudbungeecmds.cloud.cloudnet3.listener.CloudNet3ChannelMessageReceiveListener;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public final class CloudNet3ProxyCommandsModule extends NodeCloudNetModule implements ProxyCommandsAPI {

	@ModuleTask(event = ModuleLifeCycle.LOADED)
	public void onLoad() {
		loadCommandsModule();

		IEventManager eventManager = CloudNetDriver.getInstance().getEventManager();
		eventManager.registerListener(new CloudNet3ChannelMessageReceiveListener());
	}

	@ModuleTask(event = ModuleLifeCycle.UNLOADED)
	public void onUnload() {
		unloadCommandsModule();
	}

	@Override
	public ProxyConsoleCommandSender getConsoleCommandSender() {
		return new CloudNet3ProxyConsoleCommandSender();
	}

	@Override
	public ProxyPlayerCommandSender getProxyPlayerCommandSender(@NotNull UUID uuid) {
		return new CloudNet3ProxyPlayerCommandSender(uuid);
	}

	@Override
	public void sendRegisterProxyMessage(ProxyCommandInfo commandInfo) {
		CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND,
				new JsonDocument(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND_INFO, commandInfo)
		);
	}

	@Override
	public void sendUnregisterMessage(String commandName) {
		CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND,
				new JsonDocument(ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND_NAME, commandName)
		);
	}

	// TODO: SEND ALL COMMANDS IN ONE MESSAGE
	@Override
	public void registerAllCommandsOnProxy(String proxyName) {
		ServiceInfoSnapshot service = CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServiceByName(proxyName);

		for (ProxyCommandInfo commandInfo : ProxyCommandsAPI.getCommandsManagement().getBungeeCommandExecutors().keySet()) {
			CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
					service,
					ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
					ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND,
					new JsonDocument(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND_INFO, commandInfo)
			);
		}
	}

}