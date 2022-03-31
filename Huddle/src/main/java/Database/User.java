package Database;

public class User  {
	private final int id;
	private final String username; 
	private final String password;
	private final String biography;
	
	private User(UserBuilder builder) {
		this.id = builder.optionalUserId;
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
		return "\nUsername: " +  this.username
			 + "\nPassword: "  + this.password
			 + "\nBio: "       + this.biography;
	}
	
	public static class UserBuilder {
		private final String requiredUsername;
		private final String requiredPassword;
		private String optionalBiography;
		private int optionalUserId;
		
		public UserBuilder(String username, String password) {
			this.requiredUsername = username;
			this.requiredPassword = password;
		}
		
		public UserBuilder userBiography(String biography) {
			this.optionalBiography = biography;
			return this;
		}
		
		public UserBuilder userId(int id) {
			this.optionalUserId = id;
			return this;
		}
		
		public User build() {
			User user = new User(this);
			return user;
		}
	
	}
}