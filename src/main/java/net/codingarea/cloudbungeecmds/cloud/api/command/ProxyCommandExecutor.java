package net.codingarea.cloudbungeecmds.cloud.api.command;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public interface ProxyCommandExecutor {

	void execute(IProxyCommandSender sender, String command, String[] args);

}