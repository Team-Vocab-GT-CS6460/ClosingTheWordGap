package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import edu.gatech.wordgap.spring.jdbc.model.Kid;

public interface ProfilesDAO {

	List<Kid> getKids();
	Kid getKid(int id);
	void addKid(Kid newKid);
	void removeKid(int id);
	void updateKidLastActivity(int kidId);
	void updateKidSettings(int kidId, String language, String strategy, String activity);

}
