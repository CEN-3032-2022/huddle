package client;

public class AccountSaver {

	String username;
	String password;
	String recoveryAnswer1;
	String recoveryAnswer2;
	
	public AccountSaver(String username, String password, String recoveryAnswer1, String recoveryAnswer2) {
		this.username = username;
		this.password = password;
		this.recoveryAnswer1 = recoveryAnswer1;
		this.recoveryAnswer2 = recoveryAnswer2;
	}
	
	public boolean saveAccount() {
		UserRepositoryImp userRepo = new UserRepositoryImp();
		return userRepo.addNewUser(username, password, recoveryAnswer1, recoveryAnswer2);
	}
	
}
