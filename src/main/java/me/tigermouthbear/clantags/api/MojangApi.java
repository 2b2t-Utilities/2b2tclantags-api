package me.tigermouthbear.clantags.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

public class MojangApi
{
	public static String getUuid(String name)
	{
		try
		{
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
			JSONObject jsonObject = new JSONObject(new JSONTokener(new InputStreamReader(url.openStream())));
			return jsonObject.get("id").toString();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String getUsername(String uuid)
	{
		try
		{
			URL url = new URL("https://api.mojang.com/user/profiles/" + uuid + "/names");
			JSONArray jsonObject = new JSONArray(new JSONTokener(new InputStreamReader(url.openStream())));
			return ((JSONObject)jsonObject.get(0)).get("name").toString();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
