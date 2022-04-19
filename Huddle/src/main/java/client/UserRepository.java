package client;

import java.util.ArrayList;

public interface UserRepository {
	
	ArrayList<User> getAllUsers();
	
	User getUserByUsername(String username);
	
	boolean verifiyUser(String username, String password);
	
	boolean followUser(String userFollowing, String userToFollow);
	
	boolean unfollowUser(String userUnfollowing, String userToUnfollow);
	
	boolean addNewUser(String username, String password, String recAnswr1, String recAnswr2);
	
	boolean updatePassword(String username, String newPassword, String recAnswr1, String recAnswr2);
	
	boolean updateBio(String username, String newBio);
}
