package com.ibm.academia.restapi.items.modelo.servicios;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.items.clientes.ProductoClienteRest;
import com.ibm.academia.restapi.items.modelo.entidades.Articulo;

@Service("serviceFeign")
//@Primary
public class ArticuloServiceFeign implements IArticuloService
{
	@Autowired
	private ProductoClienteRest clienteFeign;
	
	@Override
	public List<Articulo> buscarTodosArticulos() 
	{
		return clienteFeign.consultarTodosProductos().stream().map(p -> new Articulo(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Articulo buscarArticuloPorId(Long productoId, Integer cantidad) 
	{
		return new Articulo(clienteFeign.consultarDetalleProducto(productoId), cantidad);
	}

}
