package net.codingarea.cloudbungeecmds.node.api.command.impl;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import de.dytanic.cloudnet.ext.bridge.player.ICloudOfflinePlayer;
import de.dytanic.cloudnet.ext.bridge.player.IPlayerManager;
import de.dytanic.cloudnet.ext.bridge.player.executor.PlayerExecutor;
import net.codingarea.cloudbungeecmds.node.api.command.IProxyCommandSender;

import java.util.UUID;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ProxyPlayerCommandSender implements IProxyCommandSender {

	static IPlayerManager playerManager = CloudNetDriver.getInstance().getServicesRegistry().getFirstService(IPlayerManager.class);

	private final UUID uuid;
	private ICloudOfflinePlayer offlinePlayer;
	private PlayerExecutor playerExecutor;
	private IPermissionUser permissionUser;

	public ProxyPlayerCommandSender(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public String getName() {
		ICloudOfflinePlayer offlinePlayer = getCloudOfflinePlayer();
		if (offlinePlayer == null) return "Unknown";
		return offlinePlayer.getName();
	}

	@Override
	public void sendMessage(String message) {
		getPlayerExecutor().sendChatMessage(message);
	}

	@Override
	public void sendMessage(String... messages) {
		for (String message : messages) {
			this.sendMessage(message);
		}
	}

	@Override
	public boolean hasPermission(String permission) {
		IPermissionUser permissionUser = getPermissionUser();
		if (permissionUser == null) return false;
		return permissionUser.hasPermission(permission).asBoolean();
	}

	public ICloudOfflinePlayer getCloudOfflinePlayer() {
		if (this.offlinePlayer != null) return this.offlinePlayer;
		this.offlinePlayer = playerManager.getOfflinePlayer(uuid);
		return this.offlinePlayer;
	}

	public PlayerExecutor getPlayerExecutor() {
		if (this.offlinePlayer != null) return this.playerExecutor;
		this.playerExecutor = playerManager.getPlayerExecutor(uuid);
		return this.playerExecutor;
	}

	public IPermissionUser getPermissionUser() {
		if (this.permissionUser != null) return this.permissionUser;
		permissionUser = CloudNetDriver.getInstance().getPermissionManagement().getUser(this.uuid);
		return permissionUser;
	}

}