package gde.example.simplerestexample;

import java.util.ArrayList;
import java.util.Collection;

public class UserDAO {
	
	private static Collection<User> users;
	
	public UserDAO(){
		users =  new ArrayList<>();
	}
	public Collection<User> list(){
		return users;
	}
	
	public User add(User user){
		users.add(user);
		return user;
	}
}
