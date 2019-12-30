package com.tigermouthbear.clantags.api;

import com.tigermouthbear.clantags.Clan;
import com.tigermouthbear.clantags.ClanMember;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

/***
 * @author Tigermouthbear
 * 12/3/19
 */

public class WebApi
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
		Clan clan = new Clan(jsonObject.get("abbreviation").toString(), jsonObject.get("full_name").toString(), jsonObject.get("description").toString());

		JSONObject members = (JSONObject) jsonObject.get("members");

		for(String name: members.keySet())
		{
			clan.addMember(getMember(name));

			for(UUID uuid: parseUUIDS(members.get(name).toString()))
			{
				getMember(name).addUuid(uuid);
			}
		}

	}

	private static ClanMember getMember(String name)
	{
		for(ClanMember member: ClanMember.getAllClanMembers())
		{
			if(member.name().equals(name)) return member;
		}

		return new ClanMember(name);
	}

	private static ArrayList<UUID> parseUUIDS(String value)
	{
		String[] uuids = value.split(", ");
		ArrayList<UUID> temp = new ArrayList<>();
		for(String uuid: uuids)
		{
			temp.add(UUID.fromString(uuid));
		}
		return temp;
	}

}
