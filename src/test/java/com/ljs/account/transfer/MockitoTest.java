/**
 * 
 */
package com.ljs.account.transfer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ljs.account.PaymentAccountApplicationTests;
import com.ljs.account.mock.dao.PersonDao;
import com.ljs.account.mock.entity.Person;
import com.ljs.account.mock.service.PersonService;

/**
 * @author lijunshi
 *
 */
public class MockitoTest extends PaymentAccountApplicationTests {

	private PersonDao mockDao;
	
	@Autowired
	private PersonService service;
	
	
	@Before
	public void setup() {
		mockDao = mock(PersonDao.class);
		when(mockDao.queryPersonById(10)).thenReturn(new Person(10,"赵四"));
		when(mockDao.updatePersonInfo(isA(Person.class))).thenReturn(true);
		service.setPersonDao(mockDao);
	}
	
	@Test
	public void testListMock() {
		boolean result = service.updatePerson(10,"刘能");
		assertEquals(true,result);
	}
}
