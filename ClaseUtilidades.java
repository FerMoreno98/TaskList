package com.List;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClaseUtilidades {
	
	public static void abrirNuevoMarco(JPanel lamina,String titulo) {
		
		JFrame nuevoMarco=new JFrame();
		
		nuevoMarco.setVisible(true);
		
		nuevoMarco.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Toolkit miPantalla=Toolkit.getDefaultToolkit();
		
		Dimension tamano=miPantalla.getScreenSize();
		
		int ancho=tamano.width;
		
		int alto=tamano.height;
		
		nuevoMarco.setBounds(ancho/4,alto/4,ancho/2,alto/2);
		
		nuevoMarco.setTitle(titulo);
		
		nuevoMarco.add(lamina);
		
		
		
	}
	
	public static String estadoDeTarea(Tarea t) {
		
		String estadoTarea;
		
		
		if(t==null) {
			return "Selecciona una tarea";
		}
		
		if(!t.getHecha()) {
		
		estadoTarea="Marcar tarea como hecha";
		
		
	
	}else {
		estadoTarea="Marcar tarea como no hecha";
		
		
	}
		
		return estadoTarea;
	}
	
	public static boolean alternarEstado(Tarea t) {
		
		boolean estado;
		
		if(t!=null) {
			t.setHecha(!t.getHecha());
			 estado=t.getHecha();
		}else {
			estado=false;
		}
		return estado;
	}

}
