package com.ibm.academia.restapi.productos.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.productos.modelo.entidades.Producto;
import com.ibm.academia.restapi.productos.modelo.servicios.ProductoDAO;

@RestController
@RequestMapping("/producto")
public class ProductoController 
{
	private final static Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoDAO productoDao;
	
	@Autowired
	private Environment environment;
	
	/**
	@Value ("${server.port}")//Lenguaje de expresion
	private Integer puerto;
	**/
	
	/**
	 * Endpoint para consultar todos los productos
	 * @return Retorna una lista de productos
	 * @author LJLV 13/07/22
	 */
	
	@GetMapping("/listar")
	public ResponseEntity<?> consultarTodosProductos()
	{
		List<Producto> productos = productoDao.buscarTodos()
				.stream()
				.map(producto ->{
					producto.setPuerto(Integer.parseInt((environment.getProperty("local.server.port"))));
					return producto;
					//producto.setPuerto(puerto);
				}).collect(Collectors.toList());

		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para consultar un objeto producto
	 * @param productoId Paremetro de busqueda del objeto
	 * @return Retorna un objeto de tipo producto
	 * @exception En caso de que falle consultando el producto
	 * @author LJLV 13/07/22
	 */
	@GetMapping("/ver-detalle/productoId/{productoId}")
	public ResponseEntity<?> consultarDetalleProducto(@PathVariable Long productoId)
	{
		Optional<Producto> producto = null;
		
		try
		{
			producto = productoDao.buscarPorId(productoId);
			producto.get().setPuerto(Integer.parseInt((environment.getProperty("local.server.port"))));
		}
		
		catch (Exception e)
		
		{
			logger.info(e.getMessage() + "Causa: " + e.getCause());
			throw e;
		}
		try 
		{
			Thread.sleep(200L);
		} 
		catch (InterruptedException e) 
		{
			logger.info(e.getMessage() + "Causa: " + e.getCause());
			e.printStackTrace();
		}
		
		return new ResponseEntity<Producto>(producto.get(), HttpStatus.OK);
	}
}

