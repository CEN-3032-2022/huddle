package client_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.Date;

public class DateTest {

	@Test
	public void testConstructors() {
		//test default constructor
		Date date1 = new Date();
		
		String correctDefaultConstructorToString = "-1/-1/-1";
		
		System.out.println(date1.toString());
		assertEquals(date1.toString(), correctDefaultConstructorToString);
			
		//test parameterized constructor
		Date date2 = new Date(2, 11, 2001);
		
		String correctParameterizedConstructorToString = "2/11/2001";
		
		System.out.println(date2.toString());
		assertEquals(date2.toString(), correctParameterizedConstructorToString);
	}
	
	@Test
	public void testGetters() {
		//test getters
		Date date1 = new Date(12, 8, 1956);

		System.out.println(date1.toString());
		assertEquals(date1.getMonth(), 12);
		assertEquals(date1.getDay(), 8);
		assertEquals(date1.getYear(), 1956);
		assertEquals(date1.getMonthName(), "December");
	}
	
	@Test
	public void testSetters() {
		//test setters
		Date date1 = new Date();
		
		date1.setMonth(5);
		date1.setDay(7);
		date1.setYear(2001);

		System.out.println(date1.toString());
		assertEquals(date1.getMonth(), 5);
		assertEquals(date1.getDay(), 7);
		assertEquals(date1.getYear(), 2001);
		assertEquals(date1.getMonthName(), "May");
	}
}
