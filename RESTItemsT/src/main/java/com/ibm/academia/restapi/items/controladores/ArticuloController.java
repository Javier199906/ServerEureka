package com.ibm.academia.restapi.items.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.items.modelo.entidades.Articulo;
import com.ibm.academia.restapi.items.modelo.entidades.Producto;
import com.ibm.academia.restapi.items.modelo.servicios.IArticuloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import feign.FeignException;

@RestController
@RequestMapping("/articulo")
public class ArticuloController 
{
	private final static Logger logger = LoggerFactory.getLogger(ArticuloController.class);
	@Autowired
	@Qualifier("serviceFeign")
	private IArticuloService articuloService;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/listar")
	public ResponseEntity<?> consultarTodosArticulos()
	{
		List<Articulo> articulos = articuloService.buscarTodosArticulos();
		return new ResponseEntity<List<Articulo>>(articulos, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param productoId
	 * @param cantidad
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/ver-detalle/productoId/{productoId}/cantidad/{cantidad}")
	public ResponseEntity<?> consultarDetalleArticulo(@PathVariable Long productoId, @PathVariable Integer cantidad)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();
		Articulo articulo = null;
		try
		{
			articulo = articuloService.buscarArticuloPorId(productoId, cantidad);
		}
		catch(FeignException fe)
		{
			logger.info("Mensaje: " + fe.getMessage() + " causa: " + fe.getCause());
			respuesta.put("mensaje", fe.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
		}
		catch(Exception e) 
		{
			logger.info("Mensaje: " + e.getMessage() + " causa: " + e.getCause());
			respuesta.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}
	
	public ResponseEntity<?> metodoAlternativo(Long productoId, Integer cantidad)
	{
		Articulo articulo = new Articulo();
		Producto producto = new Producto();
		
		articulo.setCantidad(cantidad);
		producto.setId(productoId);
		producto.setNombre("Camara SONY");
		producto.setPrecio(500.00);
		articulo.setProducto(producto);
		return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
	}
}
