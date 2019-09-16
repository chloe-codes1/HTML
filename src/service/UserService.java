package service;

import vo.UsersVO;

public interface UserService {
	
	int addUser(UsersVO user);
	int deleteUser(String id);
	int updateUser(UsersVO user);
	UsersVO login(String id, String pw);
	
	//delete user 만들깅~
	
}
