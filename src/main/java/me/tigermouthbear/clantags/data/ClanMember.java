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

	public ClanMember(String uuid)
	{
		this.uuid = uuid;
		allClanMembers.add(this);
	}

	public ArrayList<Clan> getClans()
	{
		return clans;
	}

	//Do not loop over!
	public String getName()
	{
		return MojangApi.getUsername(uuid);
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
		return getClanMemberByUuid(MojangApi.getUuid(name));
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
