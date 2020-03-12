package me.tigermouthbear.clantags.api.data;

import java.util.ArrayList;

/***
 * @author Tigermouthbear
 * 12/30/19
 */
public class ClanMember {
	private static ArrayList<ClanMember> allClanMembers = new ArrayList<>();

	private ArrayList<Clan> clans = new ArrayList<>();
	private String uuid;

	public ClanMember(String uuid) {
		this.uuid = uuid;
		allClanMembers.add(this);
	}

	public ArrayList<Clan> getClans() {
		return clans;
	}

	public String getUuid() {
		return uuid;
	}

	public static ArrayList<ClanMember> getAllClanMembers() {
		return allClanMembers;
	}
}
