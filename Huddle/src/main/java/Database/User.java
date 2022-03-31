package Database;

public class User  {
	private final String username; 
	private final String password;
	private final String biography;
	
	private User(UserBuilder builder) {
		this.username = builder.requiredUsername;
		this.password = builder.requiredPassword;
		this.biography = builder.optionalBiography;
	}

	public String fetchUsername() {
		return username;
	}
	public String fetchPassword() {
		return password;
	}
	public String fetchBiography() {
		return biography;
	}
	
	@Override
	public String toString() {
		return "User: " + this.username + ", " + this.password + ", " + this.biography;
	}
	
	public static class UserBuilder {
		private final String requiredUsername;
		private final String requiredPassword;
		private String optionalBiography;
		
		public UserBuilder(String username, String password) {
			this.requiredUsername = username;
			this.requiredPassword = password;
		}
		
		public UserBuilder userBiography(String biography) {
			this.optionalBiography = biography;
			return this;
		}
		
		public User build() {
			User user = new User(this);
			return user;
		}
	
	}
}