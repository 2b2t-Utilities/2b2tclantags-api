package com.tigermouthbear.clantags.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

public class MojangApi
{
	public static UUID getUuid(String name) throws Exception
	{
		URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
		JSONObject jsonObject = new JSONObject(new JSONTokener(new InputStreamReader(url.openStream())));
		return UUID.fromString(jsonObject.get("id").toString());
	}

	public static String getUsername(String uuid) throws Exception
	{
		URL url = new URL("https://api.mojang.com/user/profiles/" + uuid.replace("-", "") + "/names");
		JSONArray jsonArray = new JSONArray(new JSONTokener(new InputStreamReader(url.openStream())));
		JSONObject nameObject = (JSONObject) jsonArray.get(jsonArray.length()-1);
		return nameObject.get("name").toString();
	}
}
