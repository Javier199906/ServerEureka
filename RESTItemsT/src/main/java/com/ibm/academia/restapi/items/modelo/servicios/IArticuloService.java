package com.ibm.academia.restapi.items.modelo.servicios;

import java.util.List;

import com.ibm.academia.restapi.items.modelo.entidades.Articulo;

public interface IArticuloService 
{
	public List<Articulo> buscarTodosArticulos();
	public Articulo buscarArticuloPorId(Long productoId, Integer cantidad);
}
