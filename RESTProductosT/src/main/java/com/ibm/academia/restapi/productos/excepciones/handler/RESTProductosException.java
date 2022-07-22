package com.ibm.academia.restapi.productos.excepciones.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.restapi.productos.excepciones.NotFoundException;

@ControllerAdvice
public class RESTProductosException 
{
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<?> noExisteProductoException(NotFoundException exception)
	{
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("error", exception.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
	}
}
