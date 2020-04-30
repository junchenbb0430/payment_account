package com.ljs.account.mock.dao;

import com.ljs.account.mock.entity.Person;

public interface PersonDao {

	public Person  queryPersonById(int id);
	
	public boolean updatePersonInfo(Person person);
}
