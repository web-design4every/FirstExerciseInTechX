package com.springbootproject.service;

import java.util.List;

import com.springbootproject.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO userDTO,String username);
	void delete(String username);
	UserDTO get(long id);
	List<UserDTO> getList();
}
