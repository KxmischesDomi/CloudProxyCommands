package net.codingarea.cloudbungeecmds;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ProxyCommandExecuteInfo {

	private final String commandName;
	private final UUID uuid;
	private final String[] args;

	public ProxyCommandExecuteInfo(@NotNull String commandName, UUID uuid, String[] args) {
		this.commandName = commandName;
		this.uuid = uuid;
		this.args = args;
	}

	public String getCommandName() {
		return commandName;
	}

	public UUID getUUID() {
		return uuid;
	}

	public String[] getArgs() {
		return args;
	}

	@Override
	public String toString() {
		return "BungeeCommandExecuteInfo{" +
				"commandName='" + commandName + '\'' +
				", uuid=" + uuid +
				", args=" + Arrays.toString(args) +
				'}';
	}

}