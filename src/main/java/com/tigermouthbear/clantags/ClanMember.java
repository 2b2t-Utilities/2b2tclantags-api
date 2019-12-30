package com.tigermouthbear.clantags;

import com.tigermouthbear.clantags.api.MojangApi;

import java.util.ArrayList;
import java.util.UUID;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

public class ClanMember
{
	private static ArrayList<ClanMember> allClanMembers = new ArrayList<>();

	private ArrayList<Clan> clans = new ArrayList<>();
	private ArrayList<UUID> uuids;
	private String name;

	public ClanMember(String name)
	{
		this.name = name;
		allClanMembers.add(this);
	}

	public ArrayList<Clan> getClans()
	{
		return clans;
	}

	public void addUuid(UUID uuid)
	{
		uuids.add(uuid);
	}

	public ArrayList<UUID> getUuids()
	{
		return uuids;
	}

	public String name()
	{
		return name;
	}

	public static ArrayList<ClanMember> getAllClanMembers()
	{
		return allClanMembers;
	}
}
