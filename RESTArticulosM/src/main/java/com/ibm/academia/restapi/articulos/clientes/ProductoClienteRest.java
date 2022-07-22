package com.ibm.academia.restapi.articulos.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.academia.restapi.articulos.modelo.entidades.Producto;

@FeignClient(name = "api-productos")
public interface ProductoClienteRest 
{
	@GetMapping("/api/v1/rest-productos/producto/listar")
	public List<Producto> consultarTodosProductos();
	
	@GetMapping("/api/v1/rest-productos/producto/ver-detalle/productoId/{productoId}")
	public Producto  consultarDetalleProducto(@PathVariable Long productoId);
}
