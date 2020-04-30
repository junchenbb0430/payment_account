/**
 * 
 */
package com.ljs.account.mock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljs.account.mock.dao.PersonDao;
import com.ljs.account.mock.entity.Person;

/**
 * @author lijunshi
 *
 */
@Service
public class PersonService {
	
	@Autowired
	private PersonDao  personDao;
	
	public boolean updatePerson(int id,String name) {
		Person person = personDao.queryPersonById(id);
		if(null == person) {
			return false;
		}
		person.setId(id);
		person.setName(name);
		boolean result =  personDao.updatePersonInfo(person);
		return result;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	
}
