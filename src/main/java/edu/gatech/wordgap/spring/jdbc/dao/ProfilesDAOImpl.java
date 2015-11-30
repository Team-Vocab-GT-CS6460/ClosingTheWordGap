package edu.gatech.wordgap.spring.jdbc.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.gatech.wordgap.spring.jdbc.model.Kid;

public class ProfilesDAOImpl implements ProfilesDAO {

	private static final String kidsFile = "kids6.json";
	private int newKidId = 1;
	private List<Kid> kidsArray = new ArrayList<Kid>();
	
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kid> getKids() {
		System.out.println("getKids");
		List<Kid> array = null;
		
		array = getKidsFromDB();
		
		if(array == null)
		{
			array = new ArrayList<Kid>();
			
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
					String lastActivity = jsonObject.get("lastActivity").toString();
					String speech_language = jsonObject.get("speech_language").toString();
					String print_language = jsonObject.get("print_language").toString();
					String activity = jsonObject.get("activity").toString();
					String word_relationship = jsonObject.get("word_relationship").toString();
					String word_types = jsonObject.get("word_types").toString();
					String sentence_types = jsonObject.get("sentence_types").toString();
	
					Kid kid = new Kid();
					int id = Integer.parseInt(id_str);
					kid.setId(id);
					kid.setName(name);
					kid.setIcon(icon);
					kid.setLastActivity(Long.parseLong(lastActivity));
					kid.setSpeech_language(speech_language);
					kid.setPrint_language(print_language);
					kid.setActivity(activity);
					kid.setWord_relationship(word_relationship);
					kid.setWord_types(word_types);
					kid.setSentence_types(sentence_types);
					
					array.add(kid);
	
					if(id >= newKidId) {
						newKidId = id + 1;
					}
				}
			} catch(Exception e) {
				System.err.println("error while reading stored profiles: " + e.getLocalizedMessage());
			}
		}
		kidsArray = array;

		return array;
	}

	private List<Kid> getKidsFromDB() {
		
		try{
			String sql = "select * from wordgap.students";
			Object[] args = new Object[] {};
			List<Kid> array  = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<Kid>(Kid.class));
			for(Kid kid : array)
			{
				int id = kid.getId();
				if(id >= newKidId) {
					newKidId = id + 1;
				}
			}
			return array;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private boolean writeKidsToDB() {
		
		try{
			String sql = "delete from wordgap.students";
			jdbcTemplate.execute(sql);
			
			sql = "insert into wordgap.students values (?,?,?,?,?,?,?,?,?,?)";
			List<Object[]> batchArgs = new ArrayList<Object[]>();
			for(Kid kid : kidsArray)
			{
				Object[] args = new Object[] {kid.getId(), kid.getName(), kid.getIcon(), kid.getLastActivity(), 
						kid.getPrint_language(), kid.getSpeech_language(), kid.getActivity(), kid.getWord_relationship(),
						kid.getWord_types(), kid.getSentence_types()};
				batchArgs.add(args);
			}
			jdbcTemplate.batchUpdate(sql, batchArgs);
			return true;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
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
		if(!writeKidsToDB())
		{
			JSONArray kids = new JSONArray();
			try {
				for(Kid kid : kidsArray) {
					JSONObject obj = new JSONObject();
					obj.put("id", kid.getId());
					obj.put("name", kid.getName());
					obj.put("icon", kid.getIcon());
					obj.put("lastActivity", kid.getLastActivity());
					obj.put("speech_language", kid.getSpeech_language());
					obj.put("print_language", kid.getPrint_language());
					obj.put("activity", kid.getActivity());
					obj.put("word_relationship", kid.getWord_relationship());
					obj.put("word_types",kid.getWord_types());
					obj.put("sentence_types", kid.getSentence_types());
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
	}

	@Override
	public void updateKidLastActivity(int kidId) {
		System.out.println("updateKidLastActivity");
		Kid kid = getKid(kidId);
		kid.setLastActivity(System.currentTimeMillis());
		saveProfiles();
	}

	@Override
	public void updateKidSettings(int kidId, String speech_language, String print_language, 
			String word_relationship, String word_types, String sentence_types, String activity) {
		System.out.println("updateKidStrategy");
		Kid kid = getKid(kidId);
		kid.setSpeech_language(speech_language);
		kid.setPrint_language(print_language);
		kid.setWord_relationship(word_relationship);
		kid.setWord_types(word_types);
		kid.setSentence_types(sentence_types);
		kid.setActivity(activity);
		saveProfiles();
	}

}
