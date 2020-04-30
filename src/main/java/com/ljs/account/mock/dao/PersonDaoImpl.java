/**
 * 
 */
package com.ljs.account.mock.dao;

import org.springframework.stereotype.Service;

import com.ljs.account.mock.entity.Person;

/**
 * @author lijunshi
 *
 */
@Service
public class PersonDaoImpl implements PersonDao {

	@Override
	public Person queryPersonById(int id) {
		if(11 == id) {
			return new Person(11,"刘老根");
		} 
		return null;
	}

	@Override
	public boolean updatePersonInfo(Person person) {
		 if(person.getId()<11) {
			 return true;
		 }
		return false;
	}

}
