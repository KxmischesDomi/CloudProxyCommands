package net.codingarea.cloudbungeecmds.module.simplecloud.api.command.impl;

import net.codingarea.cloudbungeecmds.module.api.command.impl.ProxyConsoleCommandSender;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class SimpleCloudProxyConsoleCommandSender implements ProxyConsoleCommandSender {

	@Override
	public void sendMessage(String message) {
		System.out.println(message);
	}

}