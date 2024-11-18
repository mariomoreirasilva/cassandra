package com.devsuperior.workshopcassandra.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.workshopcassandra.model.dto.DepartmentDTO;
import com.devsuperior.workshopcassandra.model.entities.Department;
import com.devsuperior.workshopcassandra.repositories.DepartmentRepository;
import com.devsuperior.workshopcassandra.services.exceptions.ResourceNotFoundException;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository repository;
	
	public List<DepartmentDTO> findAll(){
		
		List<Department> list = repository.findAll();
		return list.stream().map(x -> new DepartmentDTO(x)).collect(Collectors.toList());
	}
	
	private Department buscaPorId(UUID id) {
		Optional<Department> resultado = repository.findById(id);
		return resultado.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
		
	}
	
	public DepartmentDTO findById(UUID id) {
		
		//Optional<Department> resultado = repository.findById(id);
		Department entidade = buscaPorId(id);
		return new DepartmentDTO(entidade);
		
	}
	
	public DepartmentDTO inserir(DepartmentDTO dto) {
		Department entity = new Department();
		entity.setId(UUID.randomUUID());
		copiaDTOparaEntidade(entity, dto);
		entity = repository.save(entity);
		return new DepartmentDTO(entity);
	}
	
	
	public DepartmentDTO atualizar(UUID id,DepartmentDTO dto) {
		Department entity = buscaPorId(id);		
		copiaDTOparaEntidade(entity, dto);
		entity = repository.save(entity);
		return new DepartmentDTO(entity);
	}

	public void copiaDTOparaEntidade(Department entity, DepartmentDTO dto) {
		entity.setName(dto.getName());
		
	}


	
	
	
}
