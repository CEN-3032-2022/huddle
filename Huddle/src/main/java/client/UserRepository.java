package client;

import java.util.ArrayList;

public interface UserRepository {
	
	ArrayList<User> getAllUsers();
	
	User getUserByUsername(String username);
	
	boolean verifiyUser(String username, String password);
	
	boolean followUser(String userFollowing, String userToFollow);
	
	boolean addNewUser(String username, String password, String recQ1, String recQ2);
}
