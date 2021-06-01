package net.codingarea.cloudbungeecmds.bungee;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.wrapper.Wrapper;
import net.codingarea.cloudbungeecmds.BungeeCommandExecuteInfo;
import net.codingarea.cloudbungeecmds.BungeeCommandInfo;
import net.codingarea.cloudbungeecmds.BungeeCommandsConstants;
import net.codingarea.cloudbungeecmds.bungee.command.BungeeCommandsCloudNetCommand;
import net.codingarea.cloudbungeecmds.bungee.listener.BungeeCommandsBungeeChannelMessageListener;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BungeeCommandsBungeePlugin extends Plugin {

	private static BungeeCommandsBungeePlugin instance;

	@Override
	public void onLoad() {
		instance = this;

		CloudNetDriver.getInstance().getEventManager().registerListener(new BungeeCommandsBungeeChannelMessageListener());
	}

	@Override
	public void onEnable() {
		sendOnEnableMessage();
	}

	public void registerCommand(BungeeCommandInfo commandInfo) {
		getProxy().getPluginManager().registerCommand(this, new BungeeCommandsCloudNetCommand(commandInfo));
	}

	public void unRegisterCommand(String name) {
		Command command = getCommandByName(name);
		if (command == null) return;
		getProxy().getPluginManager().unregisterCommand(command);
	}

	public Command getCommandByName(String name) {
		final Optional<Entry<String, Command>> commandEntry = getProxy().getPluginManager().getCommands()
				.stream().filter(entry -> entry.getKey().equals(name))
				.findFirst();
		return commandEntry.map(Entry::getValue).orElse(null);
	}

	public void sendOnEnableMessage() {
		CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
				BungeeCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				BungeeCommandsConstants.BUNGEE_COMMANDS_ON_ENABLE,
				new JsonDocument(BungeeCommandsConstants.BUNGEE_COMMANDS_COMMAND_ON_ENABLE_UUID, Wrapper.getInstance().getCurrentServiceInfoSnapshot().getServiceId().getUniqueId())
		);
	}

	public void executeCommand(@NotNull String name, UUID uuid, String[] args) {
		CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
				BungeeCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				BungeeCommandsConstants.BUNGEE_COMMANDS_EXECUTE_COMMAND,
				new JsonDocument(BungeeCommandsConstants.BUNGEE_COMMANDS_COMMAND_EXECUTE_INFO, new BungeeCommandExecuteInfo(name, uuid, args))
		);
	}

	public static BungeeCommandsBungeePlugin getInstance() {
		return instance;
	}

}