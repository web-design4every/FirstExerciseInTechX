package com.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootproject.dto.CompanyDTO;
import com.springbootproject.dto.UserDTO;
import com.springbootproject.entity.AuthenicationResponse;
import com.springbootproject.entity.User;
import com.springbootproject.service.impl.CompanyService;
import com.springbootproject.service.impl.UserDetailsServiceImpl;
import com.springbootproject.service.impl.UserService;
import com.springbootproject.ultil.JwtUtil;

@RestController
//@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	private String username;
	
	@GetMapping("/user")
	public List<UserDTO> listUser() {
		List<UserDTO> listUser = userService.getList();
		return listUser;
	}
	
//	@PostMapping("/user")
//	public UserDTO createUser(@RequestBody UserDTO model) {
//		return userService.save(model,"");
//	}
	
	@PutMapping(value = "/user")
	public UserDTO updateUser(@RequestBody UserDTO model) {
		return userService.save(model,username);
	}
	
	@GetMapping(value = "/user/{id}")
	public UserDTO getOneUser(@PathVariable("id") long id) {
		return userService.get(id);
	}
	
	@GetMapping("/company")
	public List<CompanyDTO> listCompany() {
		List<CompanyDTO> listCompany = companyService.getList();
		return listCompany;
	}
	
	@PostMapping("/company")
	public CompanyDTO createCompany(@RequestBody CompanyDTO model) {
		return companyService.save(model);
	}
	
	@PutMapping(value = "/company/{id}")
	public CompanyDTO updateCompany(@RequestBody CompanyDTO model,@PathVariable("id") long id) {
		model.setId(id);
		return companyService.save(model);
	}
	
	@GetMapping(value = "/company/{id}")
	public CompanyDTO getOneCompany(@PathVariable("id") long id) {
		return companyService.get(id);
	}
	
	@DeleteMapping(value = "/user")
	public void deleteUser() {
		userService.delete(username);
	}
	
	@PostMapping("/authenicate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO model) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(model.getUserName(), model.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(model.getUserName());
		username = model.getUserName();
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenicationResponse(jwt));
	}
}