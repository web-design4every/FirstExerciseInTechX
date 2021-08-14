package com.springbootproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootproject.entity.Company;


public interface CompanyRepository extends JpaRepository<Company, Long>{
	Company findOneByName(String name);
	Company findOneById(Long id);
	
	@Query("select c from company c where c.active=true")
	List<Company> getAllByActive();
}
