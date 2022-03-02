import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HonkTest {

	@Test
	void testConstructors() {
		//test default constructor
		Honk honk1 = new Honk();
		
		String correctDefaultConstructorToString = "id: -1\n" +
				"content: unwritten honk\n" +
				"publish date: -1/-1/-1\n";
		
		System.out.println(honk1.toString());
		assertEquals(honk1.toString(), correctDefaultConstructorToString);
			
		//test parameterized constructor 1
		Honk honk2 = new Honk(1, "testing parameterized constructor 1", new Date(12, 8, 1956));
		
		String correctParameterizedConstructor1ToString = "id: 1\n" +
				"content: testing parameterized constructor 1\n" +
				"publish date: 12/8/1956\n";
		
		System.out.println(honk2.toString());
		assertEquals(honk2.toString(), correctParameterizedConstructor1ToString);
		
		//test parameterized constructor 2
		Honk honk3 = new Honk(2, "testing parameterized constructor 2", new Date(2,11, 2001));
		
		String correctParameterizedConstructor2ToString = "id: 2\n" +
				"content: testing parameterized constructor 2\n" +
				"publish date: 2/11/2001\n";
		
		System.out.println(honk3.toString());
		assertEquals(honk3.toString(), correctParameterizedConstructor2ToString);
	}
	
	@Test
	void testGetters() {
		//test getters
		Honk honk1 = new Honk(7, "testing testing 1 2 3", new Date(5, 7, 2001));

		System.out.println(honk1.toString());
		assertEquals(honk1.getId(), 7);
		assertEquals(honk1.getContent(), "testing testing 1 2 3");
		assertEquals(honk1.getPublishDate().toString(), "5/7/2001");
	}
	
	@Test
	void testSetters() {
		//test setters
		Honk honk1 = new Honk();
			
		honk1.setId(8);
		honk1.setContent("testing setters");
		honk1.setPublishDate(new Date(8, 26, 1999));

		System.out.println(honk1.toString());
		assertEquals(honk1.getId(), 8);
		assertEquals(honk1.getContent(), "testing setters");
		assertEquals(honk1.getPublishDate().toString(), "8/26/1999");
		
		//test alternate set publish date setter
		honk1.setPublishDate(1, 1, 1900);
		System.out.println(honk1.toString());
		assertEquals(honk1.getPublishDate().toString(), "1/1/1900");
	}
}
