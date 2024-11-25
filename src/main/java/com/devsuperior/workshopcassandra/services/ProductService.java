package com.devsuperior.workshopcassandra.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.workshopcassandra.model.dto.ProductDTO;
import com.devsuperior.workshopcassandra.model.entities.Product;
import com.devsuperior.workshopcassandra.repositories.ProductRepository;
import com.devsuperior.workshopcassandra.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	

	
	private Product buscaPorId(UUID id) {
		Optional<Product> resultado = repository.findById(id);
		return resultado.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
		
	}
	
	public ProductDTO findById(UUID id) {
		
		//Optional<Product> resultado = repository.findById(id);
		Product entidade = buscaPorId(id);
		return new ProductDTO(entidade);
		
	}
	
	public List<ProductDTO> ProcuraPorDepartmnet(String departamento){
		
		List<Product> list;
		if("".equals(departamento)) {
			list = repository.findAll();
		} else {
			list = repository.findByDepartment(departamento); 
		}
				
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
	}
	
	public List<ProductDTO> procurarPorDescription(String descricao){
		
		List<Product> lista;
		if("".equals(descricao)) {
			lista = repository.findAll();
		} else {
			lista = repository.findByDescription("%"+descricao+"%"); 
		}
	//	List<Product> lista = repository.findByDescription("%"+descricao+"%");
		
		return lista.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		
	}
	
	
}
