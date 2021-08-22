package dev.tigr.clantags.api;

import dev.tigr.clantags.api.data.Clan;
import dev.tigr.clantags.api.data.ClanMember;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Tigermouthbear
 * @since 3/12/20
 */
class ClanDatabase {
	private static final String CLAN_DATABASE = "https://raw.githubusercontent.com/2b2t-Utilities/2b2tclantags-api/master/clan-database/databases.txt";

	public static void loadDatabase() {
		try {
			loadDatabase(CLAN_DATABASE);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadDatabase(String database) throws Exception {
		//Query for all individual clan databases
		URL clanDatabase = new URL(database);
		BufferedReader in = new BufferedReader(new InputStreamReader(clanDatabase.openStream()));

		ArrayList<URL> databases = new ArrayList<>();
		String inputLine;
		while((inputLine = in.readLine()) != null)
			databases.add(new URL(inputLine));
		in.close();

		//Create a clan object for each database
		for(URL url: databases) {
			JSONObject data = new JSONObject(new JSONTokener(new InputStreamReader(url.openStream())));
			makeClan(data);
		}
		// Load local clan data from the "extraclan" folder
		/*Path localdb = Paths.get("extraclan");
		if (Files.exists(localdb)) {
			File folder = new File("extraclan");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					  JSONObject data = new JSONObject(new JSONTokener(new String(Files.readAllBytes(Paths.get("extraclan/" + listOfFiles[i].getName())))));
					  makeClan(data);
				}
			}
		}*/
	}

	private static void makeClan(JSONObject jsonObject) throws Exception {
		Clan clan = new Clan(jsonObject.get("abbreviation").toString(), jsonObject.get("full_name").toString(), jsonObject.get("description").toString(), jsonObject.get("color").toString(), jsonObject.get("discord").toString());

		//Load enemies and allies
		if(jsonObject.has("allies")) {
			clan.allies = jsonObject.get("allies").toString();
		}

		if(jsonObject.has("enemies")) {
			clan.enemies = jsonObject.get("enemies").toString();
		}

		//Load members
		JSONArray members = (JSONArray) jsonObject.get("members");
		for(Object uuid: members)
			clan.addMember(newMember(uuid.toString()));
	}

	private static ClanMember newMember(String uuid) {
		for(ClanMember member: ClanMember.getAllClanMembers())
			if(member.getUuid().equals(uuid)) return member;

		return new ClanMember(uuid);
	}
}
