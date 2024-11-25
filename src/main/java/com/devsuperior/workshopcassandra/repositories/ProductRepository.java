package com.devsuperior.workshopcassandra.repositories;

import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import com.devsuperior.workshopcassandra.model.entities.Product;
import java.util.List;


public interface ProductRepository extends CassandraRepository<Product, UUID>{
	
	@AllowFiltering
	List<Product> findByDepartment(String department);

}
