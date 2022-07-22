package com.ibm.academia.restapi.productos.modelo.repositorios;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.productos.modelo.entidades.Producto;

@Repository
public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long>
{
	
}
