package net.codingarea.cloudbungeecmds.utils;

import java.util.Arrays;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class ProxyCommandInfo {

	private final String name, permission;
	private final String[] aliases;

	public ProxyCommandInfo(String name, String... aliases) {
		this(name, null, aliases);
	}

	public ProxyCommandInfo(String name, String permission, String... aliases) {
		this.name = name;
		this.permission = permission;
		this.aliases = aliases;
	}

	public String getName() {
		return name;
	}

	public String getPermission() {
		return permission;
	}

	public String[] getAliases() {
		return aliases;
	}

	@Override
	public String toString() {
		return "BungeeCommandInfo{" +
				"name='" + name + '\'' +
				", permission='" + permission + '\'' +
				", aliases=" + Arrays.toString(aliases) +
				'}';
	}
}