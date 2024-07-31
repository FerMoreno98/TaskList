package com.List;


public class Tarea {
	
	private int id;
	
	private String nombre;
	
	private String importancia;
	
	private String descripcion;
	
	private boolean hecha;
	
	

	public Tarea(String nombre, String importancia, String descripcion) {
		super();
		
		this.nombre = nombre;
		this.importancia = importancia;
		this.descripcion = descripcion;
	}
	
	public Tarea(int id,String nombre, String importancia, String descripcion) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.importancia = importancia;
		this.descripcion = descripcion;
	}
	
	public Tarea(int id,String nombre, String importancia, String descripcion,boolean hecha) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.importancia = importancia;
		this.descripcion = descripcion;
		this.hecha=false;
	}


	public int getId() {
		return id;
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
	
	public boolean getHecha() {
		return hecha;
	}
	
	public void setHecha(boolean hecha) {
		
		this.hecha=hecha;
	}
	
	
	
	  @Override
	    public String toString() {
	        return nombre + " (" + importancia + ")";
	    }
	
	
}
