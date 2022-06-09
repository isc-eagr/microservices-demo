package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface InventoryRepository extends JpaRepository<Product,Integer>{
	
	List<Product> findByNombre(String nombre);
	
	Long deleteByNombre(String nombre);

	
}
