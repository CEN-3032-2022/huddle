package client_tests;

import org.junit.Test;

import client.AccountCreationValidator;

public class AccountCreationValidatorTest {
	
	@Test
	public void testUsernameCharLengthValidator() {
		String validUsername = "twelveChrLng";
		String invalidShortUsername = "4Chr";
		String invalidLongUsername = "VeryLongUsernameThatIsTooLong";
		
		AccountCreationValidator sut = new AccountCreationValidator();
		
		assert(sut.isUsernameMeetingCharLengthReq(validUsername));
		assert(!sut.isUsernameMeetingCharLengthReq(invalidShortUsername));
		assert(!sut.isUsernameMeetingCharLengthReq(invalidLongUsername));
	}
	
	@Test
	public void testPasswordCharLengthValidator() {
		String validPassword = "twelveChrLng";
		String invalidShortPassword = "4Chr";
		String invalidLongPassword = "VeryLongPasswordThatIsTooLong";
		
		AccountCreationValidator sut = new AccountCreationValidator();
		
		assert(sut.isPwMeetingCharLengthReq(validPassword));
		assert(!sut.isPwMeetingCharLengthReq(invalidShortPassword));
		assert(!sut.isPwMeetingCharLengthReq(invalidLongPassword));
	}
	
	@Test
	public void testPasswordCapLetterValidator() {
		String validPassword = "Goodpassword123";
		String invalidPassword = "badpassword123";
		
		AccountCreationValidator sut = new AccountCreationValidator();
		
		assert(sut.isPwMeetingCapLetterReq(validPassword));
		assert(!sut.isPwMeetingCapLetterReq(invalidPassword));
	}
	
	@Test
	public void testPasswordDigitValidator() {
		String validPassword = "Goodpassword123";
		String invalidPassword = "Badpassword";
		
		AccountCreationValidator sut = new AccountCreationValidator();
		
		assert(sut.isPwMeetingDigitReq(validPassword));
		assert(!sut.isPwMeetingDigitReq(invalidPassword));
	}
}
