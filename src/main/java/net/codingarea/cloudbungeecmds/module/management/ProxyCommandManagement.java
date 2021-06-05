package net.codingarea.cloudbungeecmds.module.management;

import net.codingarea.cloudbungeecmds.utils.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.messaging.MessagingHandler;
import net.codingarea.cloudbungeecmds.module.ProxyCommandsAPI;
import net.codingarea.cloudbungeecmds.module.api.command.IProxyCommandSender;
import net.codingarea.cloudbungeecmds.module.api.command.ProxyCommandExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public final class ProxyCommandManagement {

	private final Map<ProxyCommandInfo, List<ProxyCommandExecutor>> bungeeCommandExecutors = new HashMap<>();

	public void unregisterAllCommands() {
		for (ProxyCommandInfo commandInfo : bungeeCommandExecutors.keySet()) {
			MessagingHandler.getInstance().sendMessage(
					ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
					ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND,
					MessagingHandler.getInstance().createDocument().set(ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND_NAME, commandInfo.getName())
			);
		}
	}

	public void registerCommand(@NotNull ProxyCommandInfo commandInfo, @NotNull ProxyCommandExecutor commandExecutor) {
		addExecutor(commandInfo, commandExecutor);
		MessagingHandler.getInstance().sendMessage(
				ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME,
				ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND,
				MessagingHandler.getInstance().createDocument().set(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND_INFO, commandInfo)
		);
	}

	private void addExecutor(@NotNull ProxyCommandInfo commandInfo, @NotNull ProxyCommandExecutor commandExecutor) {
		List<ProxyCommandExecutor> executors = bungeeCommandExecutors.getOrDefault(commandInfo, new ArrayList<>());
		executors.add(commandExecutor);
		bungeeCommandExecutors.put(commandInfo, executors);
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

	static IProxyCommandSender getCommandSender(UUID uuid) {
		if (uuid == null) return ProxyCommandsAPI.getModule().getConsoleCommandSender();
		return ProxyCommandsAPI.getModule().getProxyPlayerCommandSender(uuid);
	}

	public Map<ProxyCommandInfo, List<ProxyCommandExecutor>> getBungeeCommandExecutors() {
		return bungeeCommandExecutors;
	}

}