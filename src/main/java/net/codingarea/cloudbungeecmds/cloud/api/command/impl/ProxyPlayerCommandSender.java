package net.codingarea.cloudbungeecmds.cloud.api.command.impl;

import net.codingarea.cloudbungeecmds.cloud.api.command.IProxyCommandSender;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ProxyPlayerCommandSender extends IProxyCommandSender {

	@Override
	default void sendMessage(String... messages) {
		for (String message : messages) {
			this.sendMessage(message);
		}
	}

}