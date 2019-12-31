package com.tigermouthbear.clantags.data;

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
	private ArrayList<String> uuids = new ArrayList<>();
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

	public void addUuid(String uuid)
	{
		uuids.add(uuid);
	}

	public ArrayList<String> getUuids()
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

	public static ClanMember getClanMemberByName(String name)
	{
		for(ClanMember member: allClanMembers)
		{
			if(member.name.equalsIgnoreCase(name))
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
			if(member.uuids.contains(uuid))
			{
				return member;
			}
		}

		return null;
	}
}
