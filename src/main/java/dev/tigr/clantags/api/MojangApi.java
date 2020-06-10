package dev.tigr.clantags.api;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraftforge.fml.common.FMLLog;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/***
 * @author Tigermouthbear
 * @since 12/30/19
 */
public class MojangApi {
	//<UUID, Username>
	private static BiMap<String, String> playerCache = HashBiMap.create();

	public static String getUuid(String name) {
		if(playerCache.containsValue(name)) return playerCache.inverse().get(name);

		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
			JSONObject jsonObject = new JSONObject(new JSONTokener(new InputStreamReader(url.openStream())));

			String uuid = jsonObject.get("id").toString();
			playerCache.put(uuid, name);
			return uuid;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getUsername(String uuid) {
		if(playerCache.containsKey(uuid)) return playerCache.get(uuid);

		try {
			URL url = new URL("https://api.mojang.com/user/profiles/" + uuid + "/names");
			JSONArray jsonArray = new JSONArray(new JSONTokener(new InputStreamReader(url.openStream())));

			String name = ((JSONObject) jsonArray.get(jsonArray.length() - 1)).get("name").toString();
			playerCache.put(uuid, name);
			return name;
		} catch(Exception e) {
			FMLLog.log.info("UUID: " + uuid + " is not a valid account");
		}
		return null;
	}
}
