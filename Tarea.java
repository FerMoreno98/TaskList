package com.List;


public class Tarea {
	
	private String nombre;
	
	private String importancia;
	
	private String descripcion;
	
	

	public Tarea(String nombre, String importancia, String descripcion) {
		super();
		this.nombre = nombre;
		this.importancia = importancia;
		this.descripcion = descripcion;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getImportancia() {
		return importancia;
	}



	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	} 
	
	
	
	  @Override
	    public String toString() {
	        return nombre + " (" + importancia + ")";
	    }
	
	
}
