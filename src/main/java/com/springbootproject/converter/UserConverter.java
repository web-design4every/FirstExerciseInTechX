package com.springbootproject.converter;

import org.springframework.stereotype.Component;

import com.springbootproject.dto.UserDTO;
import com.springbootproject.entity.User;

@Component
public class UserConverter {

	public User toEntity(UserDTO dto) {
		User entity = new User();
		entity.setActive(dto.getActive());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		entity.setFullName(dto.getFullName());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		return entity;
	}
	
	public UserDTO toDTO(User entity) {
		UserDTO dto = new UserDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setFullName(entity.getFullName());
		dto.setUserName(entity.getUserName());
		dto.setPassword(entity.getPassword());
		dto.setActive(entity.getActive());
		dto.setPhone(entity.getPhone());
		dto.setAddress(entity.getAddress());
		dto.setCompanyId(entity.getCompany().getId());
		return dto;
	}
	
	public User toEntity(UserDTO dto,User entity) {
		entity.setActive(dto.getActive());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		entity.setFullName(dto.getFullName());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		return entity;
	}
}
