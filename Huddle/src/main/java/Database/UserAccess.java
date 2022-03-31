package Database;

import java.sql.SQLException;

public class UserAccess implements DataAccessAPI 
{
	@Override
	public  void createUser(User user) {
		try 
		{
			UserImpl.createInsert(user);
			UserImpl.successMessage(user);
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
					UserImpl.createReadAll();
			}
			catch (SQLException e) 
				{
					e.printStackTrace();
				}
	}
}