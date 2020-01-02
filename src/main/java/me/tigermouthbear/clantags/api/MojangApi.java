package me.tigermouthbear.clantags.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraftforge.fml.common.FMLLog;
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
			Gson gson = new Gson();

			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
			JsonObject jsonObject = gson.fromJson(new InputStreamReader(url.openStream()), JsonObject.class);
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
			JSONArray jsonArray = new JSONArray(new JSONTokener(new InputStreamReader(url.openStream())));
			return ((JSONObject)jsonArray.get(jsonArray.length()-1)).get("name").toString();
		}
		catch(Exception e)
		{
			FMLLog.log.info("UUID: " + uuid + " is not a valid account");
		}
		return null;
	}
}
