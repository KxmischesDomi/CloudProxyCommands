package net.codingarea.cloudbungeecmds.bungee.listener;

import de.dytanic.cloudnet.driver.event.EventListener;
import de.dytanic.cloudnet.driver.event.events.channel.ChannelMessageReceiveEvent;
import net.codingarea.cloudbungeecmds.ProxyCommandInfo;
import net.codingarea.cloudbungeecmds.ProxyCommandsConstants;
import net.codingarea.cloudbungeecmds.bungee.BungeeCommandsBungeePlugin;
import org.jetbrains.annotations.NotNull;


/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class BungeeCommandsBungeeChannelMessageListener {

	@EventListener
	public void onChannelMessageReceiver(@NotNull ChannelMessageReceiveEvent event) {
		if (!event.getChannel().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_CHANNEL_NAME)) return;
		if (event.getMessage() == null) return;
		if (event.getMessage().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND)) {
			ProxyCommandInfo commandInfo = event.getData().get(ProxyCommandsConstants.BUNGEE_COMMANDS_REGISTER_COMMAND_INFO, ProxyCommandInfo.class);
			BungeeCommandsBungeePlugin.getInstance().registerCommand(commandInfo);
		} else if (event.getMessage().equals(ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND)) {
			BungeeCommandsBungeePlugin.getInstance().unRegisterCommand(event.getData().getString(ProxyCommandsConstants.BUNGEE_COMMANDS_UNREGISTER_COMMAND_NAME));
		}
	}

}