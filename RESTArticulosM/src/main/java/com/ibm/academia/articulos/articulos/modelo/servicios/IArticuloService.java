package com.ibm.academia.articulos.articulos.modelo.servicios;

import java.util.List;

import com.ibm.academia.restapi.articulos.modelo.entidades.Articulo;

public interface IArticuloService 
{
	public List<Articulo> buscarTodosArticulos();
	public Articulo buscarArticuloPorId(Long productoId, Integer cantidad);
}
