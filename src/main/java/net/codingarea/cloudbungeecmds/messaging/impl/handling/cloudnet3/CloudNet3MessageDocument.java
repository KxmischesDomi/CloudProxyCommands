package net.codingarea.cloudbungeecmds.messaging.impl.handling.cloudnet3;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import net.codingarea.cloudbungeecmds.messaging.MessageDocument;
import org.jetbrains.annotations.NotNull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class CloudNet3MessageDocument implements MessageDocument {

	private final JsonDocument document;

	public CloudNet3MessageDocument(@NotNull JsonDocument document) {
		this.document = document;
	}

	@Override
	public @NotNull MessageDocument set(@NotNull String path, Object value) {
		document.append(path, value);
		return this;
	}

	@Override
	public String getString(@NotNull String path) {
		return document.getString(path);
	}

	@Override
	public <T> T get(@NotNull String path, @NotNull Class<T> classOfT) {
		return document.get(path, classOfT);
	}

	public JsonDocument getDocument() {
		return document;
	}
}