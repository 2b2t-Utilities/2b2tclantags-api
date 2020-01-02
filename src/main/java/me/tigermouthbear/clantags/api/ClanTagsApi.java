package me.tigermouthbear.clantags.api;

import me.tigermouthbear.clantags.data.Clan;
import me.tigermouthbear.clantags.data.ClanMember;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/***
 * @author Tigermouthbear
 * 12/3/19
 */

public class ClanTagsApi
{
	private static final String CLAN_DATABASE = "https://raw.githubusercontent.com/Tigermouthbear/2b2tclantags/master/clan-database/databases.txt";

	public static void loadClans()
	{
		try
		{
			loadClans(CLAN_DATABASE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void loadClans(String main_database) throws Exception
	{
		//Query for all individual clan databases
		URL clanDatabase = new URL(main_database);
		BufferedReader in = new BufferedReader(new InputStreamReader(clanDatabase.openStream()));

		ArrayList<URL> databases = new ArrayList<>();
		String inputLine;
		while((inputLine = in.readLine()) != null)
			databases.add(new URL(inputLine));
		in.close();

		//Create a clan object for each database
		for(URL url: databases)
		{
			loadDatabase(url);
		}
	}

	private static void loadDatabase(URL url) throws Exception
	{
		JSONObject jsonObject = new JSONObject(new JSONTokener(new InputStreamReader(url.openStream())));
		Clan clan = new Clan(jsonObject.get("abbreviation").toString(), jsonObject.get("full_name").toString(), jsonObject.get("description").toString(), jsonObject.get("color").toString(), jsonObject.get("discord").toString());

		//Load enemies and allies
		if(jsonObject.has("allies"))
		{
			clan.allies = jsonObject.get("allies").toString();
		}

		if(jsonObject.has("enemies"))
		{
			clan.enemies = jsonObject.get("enemies").toString();
		}

		//Load members
		JSONArray members = (JSONArray) jsonObject.get("members");
		for(Object uuid: members)
			clan.addMember(getMember(uuid.toString()));
	}

	private static ClanMember getMember(String uuid)
	{
		for(ClanMember member: ClanMember.getAllClanMembers())
			if(member.getUuid().equals(uuid)) return member;

		return new ClanMember(uuid);
	}
}
