/**
 * 
 */
package com.ljs.account.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

/**
 * @author lijunshi
 *
 */
public class OptionalTest {

	@Test
	public void testOptional() {
		
		 List<String> arrList = new ArrayList<String>();
		 arrList.add("A");
		 arrList.add("B");
		 arrList.add("C");
		 arrList = null;
		 Optional<List<String>> listOpt= Optional.ofNullable(arrList);
		 arrList = listOpt.orElse(new ArrayList<String>());
		 arrList.add("D");
		 for(String str:arrList) {
			 System.out.println(str);
		 }
	}
}
