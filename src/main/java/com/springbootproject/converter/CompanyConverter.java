package com.springbootproject.converter;

import org.springframework.stereotype.Component;

import com.springbootproject.dto.CompanyDTO;
import com.springbootproject.entity.Company;

@Component
public class CompanyConverter {

	public Company toEntity(CompanyDTO dto) {
		Company entity = new Company();
		entity.setName(dto.getName());
		entity.setActive(dto.getActive());
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		return entity;
	}
	
	public CompanyDTO toDTO(Company entity) {
		CompanyDTO dto = new CompanyDTO();
		if (entity.getId() != null) {
			dto.setId(entity.getId());
		}
		dto.setName(entity.getName());
		dto.setActive(entity.getActive());
		dto.setAddress(entity.getAddress());
		dto.setPhone(entity.getPhone());
		return dto;
	}
	
	public Company toEntity(CompanyDTO dto,Company entity) {
		entity.setName(dto.getName());
		entity.setActive(dto.getActive());
		entity.setAddress(dto.getAddress());
		entity.setPhone(dto.getPhone());
		return entity;
	}
}
