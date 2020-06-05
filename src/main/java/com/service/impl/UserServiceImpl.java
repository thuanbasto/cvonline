package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.User;
import com.model.UserDTO;
import com.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void addUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername().toLowerCase());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole("ROLE_USER");
		user.setEnabled((byte)1);
		userDao.addUser(user);
	}

	public void updateUser(UserDTO userDTO) {
		User user = userDao.getUserById(userDTO.getUser_ID());

		user.setAddress(userDTO.getAddress());
		user.setBirthday(userDTO.getBirthday());
		user.setEmail(userDTO.getEmail());
		user.setFacebook(userDTO.getFacebook());
		user.setName(userDTO.getName());
		user.setPhone(userDTO.getPhone());
		user.setCareer(userDTO.getCareer());
		user.setMaxim(userDTO.getMaxim());
		user.setImageUrl(userDTO.getImageUrl());
		
		userDao.updateUser(user);
	}

	public void deleteUser(int id) {
		userDao.deleteUser(id);
	}

	public UserDTO getUserById(int id) {
		User user = userDao.getUserById(id);
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUser_ID(user.getUser_ID());
		userDTO.setAddress(user.getAddress());
		userDTO.setBirthday(user.getBirthday());
		userDTO.setEmail(user.getEmail());
		userDTO.setFacebook(user.getFacebook());
		userDTO.setImageUrl(user.getImageUrl());
		userDTO.setName(user.getName());
		userDTO.setPhone(user.getPhone());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setMaxim(user.getMaxim());
		userDTO.setCareer(user.getCareer());
		
		return userDTO;
	}

	public List<UserDTO> getAllUsers() {
		List<User> listUser = userDao.getAllUsers();
		List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
		for (User user : listUser) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUser_ID(user.getUser_ID());
			userDTO.setAddress(user.getAddress());
			userDTO.setBirthday(user.getBirthday());
			userDTO.setEmail(user.getEmail());
			userDTO.setFacebook(user.getFacebook());
			userDTO.setImageUrl(user.getImageUrl());
			userDTO.setName(user.getName());
			userDTO.setPhone(user.getPhone());
			userDTO.setUsername(user.getUsername());
			
			listUserDTO.add(userDTO);
		}
		
		return listUserDTO;
	}

	public boolean checkUsername(String username) {
		return userDao.checkUsername(username);
	}

	public UserDTO getUserByUserName(String username) {
		User user = userDao.getUserByUserName(username);
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUser_ID(user.getUser_ID());
		userDTO.setAddress(user.getAddress());
		userDTO.setBirthday(user.getBirthday());
		userDTO.setEmail(user.getEmail());
		userDTO.setFacebook(user.getFacebook());
		userDTO.setImageUrl(user.getImageUrl());
		userDTO.setName(user.getName());
		userDTO.setPhone(user.getPhone());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setMaxim(user.getMaxim());
		userDTO.setCareer(user.getCareer());
		
		return userDTO;
	}
	
	public void setUserDTO(UserDTO userDTO,UserDTO user) {
		userDTO.setUser_ID(user.getUser_ID());
		userDTO.setAddress(user.getAddress());
		userDTO.setBirthday(user.getBirthday());
		userDTO.setEmail(user.getEmail());
		userDTO.setFacebook(user.getFacebook());
		userDTO.setImageUrl(user.getImageUrl());
		userDTO.setName(user.getName());
		userDTO.setPhone(user.getPhone());
		userDTO.setUsername(user.getUsername());
		userDTO.setMaxim(user.getMaxim());
		userDTO.setCareer(user.getCareer());
	}

	public void updatePassword(UserDTO userDTO) {
		User user = userDao.getUserById(userDTO.getUser_ID());
		
		user.setPassword(userDTO.getPassword());
		
		userDao.updateUser(user);
	}
}
