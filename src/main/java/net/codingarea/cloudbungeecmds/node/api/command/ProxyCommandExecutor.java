package net.codingarea.cloudbungeecmds.node.api.command;

import de.dytanic.cloudnet.command.ICommandSender;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ProxyCommandExecutor {

	void execute(ICommandSender sender, String command, String[] args);

}