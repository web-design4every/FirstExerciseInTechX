package com.springbootproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootproject.converter.CompanyConverter;
import com.springbootproject.dto.CompanyDTO;
import com.springbootproject.entity.Company;
import com.springbootproject.entity.User;
import com.springbootproject.repository.CompanyRepository;
import com.springbootproject.repository.UserRepository;
import com.springbootproject.service.ICompanyService;

@Service
public class CompanyService implements ICompanyService{

	private CompanyRepository companyRepo;
	
	private UserRepository userRepo;
	
	private CompanyConverter companyConverter;
	
	
	@Autowired
	public CompanyService(CompanyRepository companyRepo, UserRepository userRepo, CompanyConverter companyConverter) {
		super();
		this.companyRepo = companyRepo;
		this.userRepo = userRepo;
		this.companyConverter = companyConverter;
	}

	@Override
	public CompanyDTO save(CompanyDTO companyDTO) {
		Company company = new Company();
		if(companyDTO.getId() != null) {
			Company oldCompany = companyRepo.getOne(companyDTO.getId());
			company = companyConverter.toEntity(companyDTO, oldCompany);
		}else {
			company = companyConverter.toEntity(companyDTO);
		}
		if(companyDTO.getActive()==false) {
			List<User> listUser = userRepo.getAllById(companyDTO.getId());
			for(User item:listUser) {
				item.setActive(false);
			}
		}
		company = companyRepo.save(company);
		return companyConverter.toDTO(company);
	}

	@Override
	public List<CompanyDTO> getList() {
		List<Company> listCompany = companyRepo.getAllByActive();
		List<CompanyDTO> result = new ArrayList<>();
		for(Company item:listCompany) {
			CompanyDTO dto = companyConverter.toDTO(item);
			result.add(dto);
		}
		return result;
	}

	@Override
	public CompanyDTO get(long id) {
		Company company = companyRepo.findOneById(id);
		return companyConverter.toDTO(company);
	}

}
