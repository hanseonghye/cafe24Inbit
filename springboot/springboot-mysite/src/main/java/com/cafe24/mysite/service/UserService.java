package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public Boolean join(UserVo vo) {
		return userDao.insert(vo);
	}

	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo.getEmail(), userVo.getPassword());
	}

	public void update(UserVo vo) {
		userDao.update(vo);
	}

	public Boolean existEmail(String email) {
		UserVo userVo = userDao.get(email);
		return userVo != null;
	}
}
