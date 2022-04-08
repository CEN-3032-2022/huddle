package client;

import java.util.ArrayList;

public interface UserRepository {
	
	ArrayList<User> getAllUsers();
	
	User getUserByUsername(String username);
	
	User getVerifiedUser();
	
	boolean followUser(String userFollowing, String userToFollow);
	
	boolean addNewUser();
}
