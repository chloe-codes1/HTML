package service;

import dao.UsersDao;
import vo.UsersVO;

public class UserServiceImpl implements UserService {

	UsersDao dao;

	public UserServiceImpl() {}
	public UserServiceImpl(UsersDao dao) {
		super();
		this.dao = dao;
	}

	public UsersDao getDao() {
		return dao;
	}
	public void setDao(UsersDao dao) {
		this.dao = dao;
	}
	
	@Override
	public int addUser(UsersVO user) {
		return dao.insertUser(user);
	}

	@Override
	public int updateUser(UsersVO user) {
		return dao.updateUser(user);
	}

	@Override
	public UsersVO login(String id, String pw) {

		return dao.login(id, pw);
	}
	@Override
	public int deleteUser(String id) {
		return dao.deleteUser(id);
	}
	
}
