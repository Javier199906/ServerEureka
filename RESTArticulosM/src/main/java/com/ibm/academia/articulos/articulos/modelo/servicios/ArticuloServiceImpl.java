package com.ibm.academia.articulos.articulos.modelo.servicios;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.academia.restapi.articulos.modelo.entidades.Articulo;
import com.ibm.academia.restapi.articulos.modelo.entidades.Producto;

@Service("serviceRestTemplate")
public class ArticuloServiceImpl implements IArticuloService
{
	@Autowired
	private RestTemplate clienteRest;
	
	@Override
	public List<Articulo> buscarTodosArticulos() 
	{
		List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://api-productos/api/v1/rest-productos/producto/listar", Producto[].class));
		
		return productos
				.stream()
				.map(p -> new Articulo(p, 1))
				.collect(Collectors.toList());
	}

	@Override
	public Articulo buscarArticuloPorId(Long productoId, Integer cantidad)
	{
	Map<String, String> Variables = new HashMap<String, String>();
	Variables.put("productoId", productoId.toString());
	Producto producto = clienteRest.getForObject("http://api-productos/api/v1/rest-productos/producto/ver-detalle/productoId/{productoId}", Producto.class, Variables);
	return new Articulo(producto, cantidad);
	}

}
