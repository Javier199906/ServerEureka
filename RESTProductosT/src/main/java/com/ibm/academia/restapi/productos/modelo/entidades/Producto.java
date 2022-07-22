package com.ibm.academia.restapi.productos.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede ser vacio")
	@NotNull(message = "No puede ser nulo")
	@Size(min = 3, max = 80)
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Positive(message = "Debe ser mayor a 0")
	@Column(name= "precio", nullable = false)
	private Double precio;
	
	@NotEmpty(message = "No puede ser vacio")
	@NotNull(message = "No puede ser nulo")
	@Size(min = 3, max = 100)
	@Column(name = "usuario_creacion", nullable = false)
	private String usuarioCreacion;
	
	@Column(name = "fecha_creacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Transient
	private Integer puerto;
	private static final long serialVersionUID = 1L;
}
