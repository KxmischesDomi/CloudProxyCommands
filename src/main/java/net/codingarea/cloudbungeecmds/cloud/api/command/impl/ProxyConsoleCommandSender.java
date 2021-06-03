package net.codingarea.cloudbungeecmds.cloud.api.command.impl;

import com.google.common.base.Preconditions;
import net.codingarea.cloudbungeecmds.cloud.api.command.IProxyCommandSender;

/**
 *
 * A copied version of {@link de.dytanic.cloudnet.command.ConsoleCommandSender} to not use CloudNet classes for future cloud systems support
 *
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ProxyConsoleCommandSender extends IProxyCommandSender {

	@Override
	default String getName() {
		return "Console";
	}

	@Override
	default void sendMessage(String... messages) {
		Preconditions.checkNotNull(messages);

		for (String message : messages) {
			this.sendMessage(message);
		}
	}

	/**
	 * The console as always the permission for by every request
	 */
	@Override
	default boolean hasPermission(String permission) {
		return true;
	}

}