package com.example.demo.controller;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.InventoryRepository;

@Transactional
@RestController
public class InventoryController {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@PostMapping("/inventario/producto/{nombre}/{precio}/{stock}")
	public Product agregarProducto(@PathVariable String nombre,
									@PathVariable double precio,
									@PathVariable int stock) {
		
		Product p = new Product(nombre,precio,stock);
		inventoryRepository.save(p);
		p.setMessage("El producto fue guardado correctamente");
		return p;
	}
	
	@DeleteMapping("/inventario/producto/{nombre}")
	public void borrarProducto(@PathVariable String nombre) {
		inventoryRepository.deleteByNombre(nombre);
	}
	
	@GetMapping("/inventario/producto/{nombre}")
	public Product consultarProducto(@PathVariable String nombre) {
		return inventoryRepository.findByNombre(nombre).get(0);
	}
	
	@PutMapping("/inventario/producto/{nombre}/{precio}/{stock}")
	public Product modificarProducto(@PathVariable String nombre,
									@PathVariable double precio,
									@PathVariable int stock) {
		
		Product p = inventoryRepository.findByNombre(nombre).get(0);
		p.setPrecio(precio);
		p.setStock(stock);
		inventoryRepository.save(p);
		p.setMessage("El producto fue modificado correctamente");
		return p;
	}
	

}
