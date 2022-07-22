package com.ibm.academia.restapi.productos.modelo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.productos.excepciones.NotFoundException;
import com.ibm.academia.restapi.productos.modelo.entidades.Producto;
import com.ibm.academia.restapi.productos.modelo.repositorios.ProductoRepository;
@Service
public class ProductoDAOImpl implements ProductoDAO 
{
	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> buscarTodos() 
	{
		return (List<Producto>) productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> buscarPorId(Long id) 
	{
	     Optional<Producto> producto = productoRepository.findById(id);
	if(!producto.isPresent())
		throw new NotFoundException(String.format("El producto con ID %d no existe", id));
	
	return producto;
	}

}
