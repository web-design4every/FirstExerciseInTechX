package com.springbootproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springbootproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findOneById(Long id);
	
	@Query("FROM user WHERE username=:username")
	User findOneByUsername(@Param("username") String username);
	
	@Query("select u from user u where u.company.id=?1")
	List<User> getAllById(Long id);
	
	@Query("select u from user u where u.active=true")
	List<User> getAllByActive();
}
