package com.ibm.academia.restapi.productos.modelo.servicios;
import java.util.List;
import java.util.Optional;

import com.ibm.academia.restapi.productos.modelo.entidades.Producto;

public interface ProductoDAO 
{
	public List<Producto> buscarTodos();
	public Optional<Producto> buscarPorId(Long id);
}
