package client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class AccountCreationValidator {

	private Pattern usernameCharLengthPattern;
	private Pattern pwCapLetterPattern;
	private Pattern pwDigitPattern;
	private Pattern pwCharLengthPattern;
	
	public AccountCreationValidator() {
		setUsernameCharLengthPattern("^.{5,20}$");
		setPwCapLetterPattern("[A-Z]+");
		setPwDigitPattern("[0-9]+");
		setPwCharLengthPattern("^.{7,20}$");
	}
	
	public boolean isValidAccount(String username, String password) {
		return (isValidUsername(username) && isValidPassword(password));
	}
	
	public boolean isValidUsername(String username) {
		return (isUsernameMeetingCharLengthReq(username) && isUsernameAvailable(username));
	}
	
	public boolean isValidPassword(String password) {
		return isPwMeetingCapLetterReq(password) &&
				isPwMeetingDigitReq(password) &&
				isPwMeetingCharLengthReq(password);
	}
	
	public boolean isUsernameAvailable(String username) {
		
//		JSONObject checkUsernameJSON = getCheckUsernameJSONRequest(username);
		
		// TODO: Send checkUsername request and retrieve response
		
		return true;
	}

	public boolean isUsernameMeetingCharLengthReq(String username) {
		Matcher usernameCharLengthMatcher = usernameCharLengthPattern.matcher(username);
		return usernameCharLengthMatcher.find();
	}
	
	public boolean isPwMeetingCapLetterReq(String password) {
		Matcher pwCapLetterMatcher = pwCapLetterPattern.matcher(password);
		return pwCapLetterMatcher.find();
	}
	
	public boolean isPwMeetingDigitReq(String password) {
		Matcher pwDigitMatcher = pwDigitPattern.matcher(password);
		return pwDigitMatcher.find();
	}
	
	public boolean isPwMeetingCharLengthReq(String password) {
		Matcher pwCharLengthMatcher = pwCharLengthPattern.matcher(password);
		return pwCharLengthMatcher.find();
	}
	
	public void setUsernameCharLengthPattern(String patternRegex) {
		this.usernameCharLengthPattern = Pattern.compile(patternRegex);
	}

	public void setPwCapLetterPattern(String patternRegex) {
		this.pwCapLetterPattern = Pattern.compile(patternRegex);
	}

	public void setPwDigitPattern(String patternRegex) {
		this.pwDigitPattern = Pattern.compile(patternRegex);
	}

	public void setPwCharLengthPattern(String patternRegex) {
		this.pwCharLengthPattern = Pattern.compile(patternRegex);
	}
	
}
