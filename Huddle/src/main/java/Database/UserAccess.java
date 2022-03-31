package Database;

import java.sql.SQLException;

public class UserAccess implements DataAccessAPI 
{
	@Override
	public  void createUser(User user) {
		String username = user.fetchUsername();
		String password = user.fetchPassword();
		String biography = user.fetchBiography();
		try 
		{
			UserImpl.createInsert().setString(1, username);
			UserImpl.createInsert().setString(2, password);
			UserImpl.createInsert().setString(3, biography);
			UserImpl.successMessage(username);
			UserImpl.createInsert().executeUpdate();
			UserImpl.createInsert().close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void readAllUsers() 
	{
			try
			{
				while ( UserImpl.createReadAll().next() ) 
				{
					int id = UserImpl.createReadAll().getInt("id");
					String username = UserImpl.createReadAll().getString("username");
					String password = UserImpl.createReadAll().getString("password");
					String biography = UserImpl.createReadAll().getString("biography");
					UserImpl.format(id, username, password, biography);
					UserImpl.createReadAll().close();
				}
			}
			catch (SQLException e) 
				{
					e.printStackTrace();
				}
	}
}