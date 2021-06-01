package net.codingarea.cloudbungeecmds.node;

import de.dytanic.cloudnet.command.ConsoleCommandSender;
import de.dytanic.cloudnet.command.ICommandSender;
import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import net.codingarea.cloudbungeecmds.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.node.api.command.ProxyCommandExecutor;
import net.codingarea.cloudbungeecmds.node.api.command.impl.ProxyPlayerCommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public final class ProxyCommandsManagement {

	private final Map<ProxyCommandInfo, List<ProxyCommandExecutor>> bungeeCommandExecutors = new HashMap<>();

	public void unregisterCommand(String name) {
		CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND,
				new JsonDocument(ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND_NAME, name)
		);
	}

	public void unregisterAllCommands() {
		for (ProxyCommandInfo commandInfo : bungeeCommandExecutors.keySet()) {
			unregisterCommand(commandInfo.getName());
		}
	}

	public void registerCommand(@NotNull ProxyCommandInfo commandInfo, @NotNull ProxyCommandExecutor commandExecutor) {
		addExecutor(commandInfo, commandExecutor);
		sendRegisterProxyMessage(commandInfo);
	}

	private void addExecutor(@NotNull ProxyCommandInfo commandInfo, @NotNull ProxyCommandExecutor commandExecutor) {
		List<ProxyCommandExecutor> executors = bungeeCommandExecutors.getOrDefault(commandInfo, new ArrayList<>());
		executors.add(commandExecutor);
		bungeeCommandExecutors.put(commandInfo, executors);
	}

	private void sendRegisterProxyMessage(@NotNull ProxyCommandInfo commandInfo) {
		CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND,
				new JsonDocument(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND_INFO, commandInfo)
		);
	}

	// TODO: SEND ALL COMMANDS IN ONE MESSAGE
	public void registerAllCommandsOnProxy(ServiceInfoSnapshot serviceInfoSnapshot) {
		for (ProxyCommandInfo commandInfo : bungeeCommandExecutors.keySet()) {
			CloudNetDriver.getInstance().getMessenger().sendChannelMessage(
					serviceInfoSnapshot,
					ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
					ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND,
					new JsonDocument(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND_INFO, commandInfo)
			);
		}
	}

	public void executeCommand(@NotNull String name, UUID uuid, String[] args) {
		if (args == null) args = new String[0];
		List<ProxyCommandExecutor> commandExecutors = getExecutor(name);
		for (ProxyCommandExecutor executor : commandExecutors) {
			executor.execute(getCommandSender(uuid), name, args);
		}
	}
	
	public List<ProxyCommandExecutor> getExecutor(@NotNull String name) {
		return bungeeCommandExecutors.entrySet().stream()
				.filter(entry -> entry.getKey().getName().equals(name))
				.map(Entry::getValue)
				.findFirst().orElse(new ArrayList<>());
	}

	static ICommandSender getCommandSender(UUID uuid) {
		if (uuid == null) return new ConsoleCommandSender(CloudNetDriver.getInstance().getLogger());
		return new ProxyPlayerCommandSender(uuid);
	}

}