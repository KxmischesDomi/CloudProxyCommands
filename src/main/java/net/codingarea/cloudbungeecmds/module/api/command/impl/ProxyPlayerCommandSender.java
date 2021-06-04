package net.codingarea.cloudbungeecmds.module.api.command.impl;

import net.codingarea.cloudbungeecmds.module.api.command.IProxyCommandSender;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ProxyPlayerCommandSender extends IProxyCommandSender {

	UUID getUUID();

}