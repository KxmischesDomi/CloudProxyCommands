package net.codingarea.cloudbungeecmds.messaging.impl.handling.simplecloud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.codingarea.cloudbungeecmds.messaging.MessageDocument;
import org.jetbrains.annotations.NotNull;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
public class SimpleCloudMessageDocument implements MessageDocument {

	private static final Gson GSON = new GsonBuilder().create();
	private final JsonObject object;

	public SimpleCloudMessageDocument(@NotNull JsonObject object) {
		this.object = object;
	}

	@Override
	public @NotNull MessageDocument set(@NotNull String path, Object value) {
		if (value == null) {
			return this;
		}

		this.object.add(path, GSON.toJsonTree(value));
		return this;
	}

	@Override
	public String getString(@NotNull String path) {
		if (!object.has(path)) {
			return null;
		}

		JsonElement jsonElement = object.get(path);

		if (jsonElement.isJsonPrimitive()) {
			return jsonElement.getAsString();
		} else {
			return null;
		}
	}

	@Override
	public <T> T get(@NotNull String path, @NotNull Class<T> classOfT) {
		JsonElement jsonElement = this.get(path);

		if (jsonElement == null) {
			return null;
		} else {
			return GSON.fromJson(jsonElement, classOfT);
		}
	}

	public JsonElement get(String key) {
		if (!object.has(key)) {
			return null;
		}

		return object.get(key);
	}

	public JsonObject getObject() {
		return object;
	}
}