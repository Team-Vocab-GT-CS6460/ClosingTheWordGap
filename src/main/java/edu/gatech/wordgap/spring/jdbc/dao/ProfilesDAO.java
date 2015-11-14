package edu.gatech.wordgap.spring.jdbc.dao;

import java.util.List;

import edu.gatech.wordgap.spring.jdbc.model.Kid;

public interface ProfilesDAO {

	List<Kid> getKids();
	void addKid(Kid newKid);
	Kid getKid(int id);

}
