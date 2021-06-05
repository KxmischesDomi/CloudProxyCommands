package net.codingarea.cloudbungeecmds.module.simplecloud;

import eu.thesimplecloud.api.external.ICloudModule;
import net.codingarea.cloudbungeecmds.module.ProxyCommandsAPI;
import net.codingarea.cloudbungeecmds.module.api.command.impl.ProxyConsoleCommandSender;
import net.codingarea.cloudbungeecmds.module.api.command.impl.ProxyPlayerCommandSender;
import net.codingarea.cloudbungeecmds.module.simplecloud.api.command.impl.SimpleCloudProxyConsoleCommandSender;
import net.codingarea.cloudbungeecmds.module.simplecloud.api.command.impl.SimpleCloudProxyPlayerCommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class SimpleCloudProxyCommandsModule implements ICloudModule, ProxyCommandsAPI {

	@Override
	public boolean isReloadable() {
		return true;
	}

	@Override
	public void onDisable() {
		try {
			unloadCommandsModule();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onEnable() {
		try {
			loadCommandsModule();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ProxyConsoleCommandSender getConsoleCommandSender() {
		return new SimpleCloudProxyConsoleCommandSender();
	}

	@Override
	public ProxyPlayerCommandSender getProxyPlayerCommandSender(@NotNull UUID uuid) {
		return new SimpleCloudProxyPlayerCommandSender(uuid);
	}

}