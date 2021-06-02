package net.codingarea.cloudbungeecmds.node.api.command.impl;

import com.google.common.base.Preconditions;
import de.dytanic.cloudnet.common.logging.ILogger;
import de.dytanic.cloudnet.common.logging.LogLevel;
import net.codingarea.cloudbungeecmds.node.api.command.IProxyCommandSender;

/**
 *
 * A copied version of {@link de.dytanic.cloudnet.command.ConsoleCommandSender} to not use CloudNet classes for future cloud systems support
 *
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ProxyConsoleCommandSender implements IProxyCommandSender {

	private final ILogger logger;

	public ProxyConsoleCommandSender(ILogger logger) {
		this.logger = logger;
	}

	/**
	 * The console name is the codename of the current CloudNet version
	 */
	@Override
	public String getName() {
		return "Console";
	}

	@Override
	public void sendMessage(String message) {
		this.logger.log(LogLevel.COMMAND, message);
	}

	@Override
	public void sendMessage(String... messages) {
		Preconditions.checkNotNull(messages);

		for (String message : messages) {
			this.sendMessage(message);
		}
	}

	/**
	 * The console as always the permission for by every request
	 */
	@Override
	public boolean hasPermission(String permission) {
		return true;
	}

}