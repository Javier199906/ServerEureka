package com.ibm.academia.restapi.items.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Producto implements Serializable
{
	private Long id;
	private String nombre;
	private Double precio;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private Integer puerto;
	
	private static final long serialVersionUID = 1L;
}
