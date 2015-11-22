package edu.gatech.wordgap.spring.jdbc.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.gatech.wordgap.spring.jdbc.model.Kid;

public class ProfilesDAOImpl implements ProfilesDAO {

	private static final String kidsFile = "kids4.json";
	private int newKidId = 1;
	private List<Kid> kidsArray = new ArrayList<Kid>();

	@SuppressWarnings("unchecked")
	@Override
	public List<Kid> getKids() {
		System.out.println("getKids");
		ArrayList<Kid> array = new ArrayList<Kid>();
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(kidsFile));
			JSONArray kids = (JSONArray) obj;
			Iterator<Object> iterator = kids.iterator();
			while(iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject)iterator.next();
				String id_str = jsonObject.get("id").toString();
				String name = jsonObject.get("name").toString();
				String icon = jsonObject.get("icon").toString();
				String language = jsonObject.get("language").toString();
				String lastActivity = jsonObject.get("lastActivity").toString();

				Kid kid = new Kid();
				int id = Integer.parseInt(id_str);
				kid.setId(id);
				kid.setName(name);
				kid.setLanguage(language);
				kid.setIcon(icon);
				kid.setLastActivity(Long.parseLong(lastActivity));

				array.add(kid);

				if(id >= newKidId) {
					newKidId = id + 1;
				}
			}
		} catch(Exception e) {
			System.err.println("error while reading stored profiles: " + e.getLocalizedMessage());
		}
		kidsArray = array;

		return array;
	}

	@Override
	public void addKid(Kid newKid) {
		System.out.println("addKid");
		newKid.setId(newKidId);
		boolean success = kidsArray.add(newKid);
		if(!success) {
			System.err.println("error while adding kid: " + newKidId);
		}
		saveProfiles();
	}

	@Override
	public Kid getKid(int id) {
		System.out.println("getKid");
		for(Kid kid : kidsArray) {
			if(kid.getId() == id) {
				return kid;
			}
		}
		System.err.println("error while getting kid: " + id);
		return null;
	}

	@Override
	public void removeKid(int id) {
		System.out.println("removeKid");
		Kid kid = getKid(id);
		boolean success = kidsArray.remove(kid);
		if(!success) {
			System.err.println("error while removing kid: " + id);
		}
		saveProfiles();
	}

	@SuppressWarnings("unchecked")
	private void saveProfiles() {
		System.out.println("saveProfiles");
		JSONArray kids = new JSONArray();
		try {
			for(Kid kid : kidsArray) {
				JSONObject obj = new JSONObject();
				obj.put("id", kid.getId());
				obj.put("name", kid.getName());
				obj.put("icon", kid.getIcon());
				obj.put("language", kid.getLanguage());
				obj.put("lastActivity", kid.getLastActivity());
				kids.add(obj);
			}
			FileWriter fileWriter = new FileWriter(kidsFile);
			fileWriter.write(kids.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			System.err.println("error while saving profile: " + e.getLocalizedMessage());
		}
	}

	@Override
	public void updateKidLastActivity(int kidId) {
		System.out.println("updateKidLastActivity");
		Kid kid = getKid(kidId);
		kid.setLastActivity(System.currentTimeMillis());
		saveProfiles();
	}

}
