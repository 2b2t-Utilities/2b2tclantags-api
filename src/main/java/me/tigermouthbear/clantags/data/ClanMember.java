package me.tigermouthbear.clantags.data;

import me.tigermouthbear.clantags.api.MojangApi;

import java.util.ArrayList;

/***
 * @author Tigermouthbear
 * 12/30/19
 */

public class ClanMember
{
	private static ArrayList<ClanMember> allClanMembers = new ArrayList<>();

	private ArrayList<Clan> clans = new ArrayList<>();
	private String uuid;
	private String name;

	public ClanMember(String uuid)
	{
		this.name = MojangApi.getUsername(uuid);
		this.uuid = uuid;
		allClanMembers.add(this);
	}

	public ArrayList<Clan> getClans()
	{
		return clans;
	}

	public String getName()
	{
		return name;
	}

	public String getUuid()
	{
		return uuid;
	}

	public static ArrayList<ClanMember> getAllClanMembers()
	{
		return allClanMembers;
	}

	public static ClanMember getClanMemberByUsername(String name)
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
			if(member.uuid.equalsIgnoreCase(uuid))
			{
				return member;
			}
		}

		return null;
	}
}
