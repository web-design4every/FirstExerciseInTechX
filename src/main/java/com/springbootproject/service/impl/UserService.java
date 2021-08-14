package com.springbootproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootproject.converter.UserConverter;
import com.springbootproject.dto.UserDTO;
import com.springbootproject.entity.Company;
import com.springbootproject.entity.User;
import com.springbootproject.repository.CompanyRepository;
import com.springbootproject.repository.UserRepository;
import com.springbootproject.service.IUserService;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public UserDTO save(UserDTO userDTO,String username) {
		User user = new User();
//		if(userDTO.getId() != null && username != "") {
			User oldUser = userRepo.findOneByUsername(username);
			user = userConverter.toEntity(userDTO, oldUser);
//		}
//		else {
//			user = userConverter.toEntity(userDTO);
//		}
		Company company = companyRepo.findOneById(userDTO.getCompanyId());
		user.setCompany(company);
		user = userRepo.save(user);
		return userConverter.toDTO(user);
	}

	@Override
	public void delete(String username) {
		User user = userRepo.findOneByUsername(username);
		user.setActive(false);
		user = userRepo.save(user);
	}

	@Override
	public UserDTO get(long id) {
		User user = userRepo.findOneById(id);		
		return userConverter.toDTO(user);
	}

	@Override
	public List<UserDTO> getList() {
		List<User> listUser= userRepo.getAllByActive();
		List<UserDTO> results = new ArrayList<>();
		for(User item:listUser) {
			UserDTO newDTO = userConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}
}
