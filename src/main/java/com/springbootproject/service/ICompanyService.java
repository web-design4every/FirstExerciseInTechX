package com.springbootproject.service; 

import java.util.List;

import com.springbootproject.dto.CompanyDTO;

public interface ICompanyService {
	CompanyDTO save(CompanyDTO companyDTO);
	List<CompanyDTO> getList();
	CompanyDTO get(long id);
}
