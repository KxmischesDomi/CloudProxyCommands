package net.codingarea.cloudbungeecmds.module.cloudnet3;

import de.dytanic.cloudnet.driver.module.ModuleLifeCycle;
import de.dytanic.cloudnet.driver.module.ModuleTask;
import de.dytanic.cloudnet.module.NodeCloudNetModule;
import net.codingarea.cloudbungeecmds.module.ProxyCommandsAPI;
import net.codingarea.cloudbungeecmds.module.api.command.impl.ProxyConsoleCommandSender;
import net.codingarea.cloudbungeecmds.module.api.command.impl.ProxyPlayerCommandSender;
import net.codingarea.cloudbungeecmds.module.cloudnet3.api.command.impl.CloudNet3ProxyConsoleCommandSender;
import net.codingarea.cloudbungeecmds.module.cloudnet3.api.command.impl.CloudNet3ProxyPlayerCommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public final class CloudNet3ProxyCommandsModule extends NodeCloudNetModule implements ProxyCommandsAPI {

	@ModuleTask(event = ModuleLifeCycle.LOADED)
	public void onLoad() {
		loadCommandsModule();
	}

	@ModuleTask(event = ModuleLifeCycle.UNLOADED)
	public void onUnload() {
		unloadCommandsModule();
	}

	@Override
	public ProxyConsoleCommandSender getConsoleCommandSender() {
		return new CloudNet3ProxyConsoleCommandSender();
	}

	@Override
	public ProxyPlayerCommandSender getProxyPlayerCommandSender(@NotNull UUID uuid) {
		return new CloudNet3ProxyPlayerCommandSender(uuid);
	}

}