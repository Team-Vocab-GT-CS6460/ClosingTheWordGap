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

	private static final String kidsFile = "kids.json";

	@Override
	public List<Kid> getKids() {
		ArrayList<Kid> kidsArray = new ArrayList<Kid>();
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(kidsFile));
			JSONArray kids = (JSONArray) obj;
			Iterator<Object> iterator = kids.iterator();
			while(iterator.hasNext()) {
				JSONObject jsonObject =  (JSONObject)iterator.next();
				String name = (String) jsonObject.get("name");
				String language = (String) jsonObject.get("language");
				String points = jsonObject.get("points").toString();
				String lastActivity = jsonObject.get("lastActivity").toString();
				Kid kid = new Kid();
				kid.setName(name);
				kid.setLanguage(language);
				kid.setPoints(Integer.parseInt(points));
				kid.setLastActivity(Long.parseLong(lastActivity));
				kidsArray.add(kid);
			}

		} catch(Exception e) {
			System.err.println("error while reading stored profiles: " + e.getLocalizedMessage());
		}
		return kidsArray;
	}

	@Override
	public void addKid(Kid newKid) {
		List<Kid> kids = getKids();
		kids.add(newKid);
		JSONArray array = new JSONArray();
		for(Kid kid : kids) {
			JSONObject obj = new JSONObject();
			obj.put("name", kid.getName());
			obj.put("language", kid.getLanguage());
			obj.put("points", kid.getPoints());
			obj.put("lastActivity", kid.getLastActivity());
			array.add(obj);
		}
		try {
			FileWriter fileWriter = new FileWriter(kidsFile);
			fileWriter.write(array.toJSONString());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			System.err.println("error while saving new profile: " + e.getLocalizedMessage());
		}
	}

}
