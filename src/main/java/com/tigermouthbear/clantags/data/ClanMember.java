package com.tigermouthbear.clantags.data;

import com.tigermouthbear.clantags.api.MojangApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

public class ClanMember
{
	private static ArrayList<ClanMember> allClanMembers = new ArrayList<>();

	private ArrayList<Clan> clans = new ArrayList<>();
	private Map<String, String> users = new HashMap<>();
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

	public void addUser(String uuid)
	{
		users.put(MojangApi.getUsername(uuid), uuid);
	}

	public Map<String, String> getUsers()
	{
		return users;
	}

	public String name()
	{
		return name;
	}

	public static ArrayList<ClanMember> getAllClanMembers()
	{
		return allClanMembers;
	}

	public static ClanMember getClanMemberByUsername(String name)
	{
		for(ClanMember member: allClanMembers)
		{
			if(member.users.keySet().contains(name))
			{
				return member;
			}
		}

		return null;
	}

	public static ClanMember getClanMemberByUuid(String uuid)
	{
		for(ClanMember member: allClanMembers)
		{
			if(member.users.values().contains(uuid))
			{
				return member;
			}
		}

		return null;
	}
}
