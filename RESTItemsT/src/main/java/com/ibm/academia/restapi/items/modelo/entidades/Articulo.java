package com.ibm.academia.restapi.items.modelo.entidades;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Articulo implements Serializable
{
	private Producto producto;
	private Integer cantidad;
	
	public Articulo(Producto producto, Integer cantidad) 
	{
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public Double getTotal() 
	{
		return producto.getPrecio() * cantidad.doubleValue();
	}

	private static final long serialVersionUID = 1L;
}
