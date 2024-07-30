package com.List;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class Graficos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoTarea m=new MarcoTarea();
		
		m.setVisible(true);
		
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

class MarcoTarea extends JFrame{
	
	private LaminaBuscador laminaBuscador;
	
	public MarcoTarea(){
	
	Toolkit miPantalla=Toolkit.getDefaultToolkit();
	
	Dimension tamano=miPantalla.getScreenSize();
	
	int ancho=tamano.width;
	
	int alto=tamano.height;
	
	setBounds(ancho/4,alto/4,ancho/2,alto/2);
	
	setTitle("Gestor de tareas");
	
	laminaBuscador=new LaminaBuscador();
	
	add(new LaminaTarea(laminaBuscador));
	
	
	
	}
	
}

class LaminaTarea extends JPanel{
	
	private LaminaBuscador laminaBuscador;
	
	public LaminaTarea(LaminaBuscador laminaBuscador) {
		
		this.laminaBuscador=laminaBuscador;
		
		//cambiamos el layout para colocar los elementos en el centro
		setLayout(new BorderLayout());
		//iniciamos un box para colocar mis elementos en vertical
		Box cajaInicio=Box.createVerticalBox();
		
		add(cajaInicio);
		//añadimos una etiqueta para gestionar las tareas
		Font miFuente=new Font("Arial",Font.BOLD,20);
		
		JLabel intro=new JLabel("Bienvenido a su gestor de tareas");
		
		intro.setFont(miFuente);
		
		intro.setAlignmentX(Component.CENTER_ALIGNMENT);//a cada elemento de la caja le añadimos esta instruccion para que se coloque en el centro
		
		cajaInicio.add(intro);
		
		cajaInicio.add(Box.createVerticalStrut(50));//con esto genereamos un espacio entre elementos
		
		
		
		
		//------------------------------------------------------
		
		//añadimos el boton de añadir tareas a la caja Inicial
		
		JButton aniadirTarea=new JButton("Añadir tarea");
		
		aniadirTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cajaInicio.add(aniadirTarea);
		
		cajaInicio.add(Box.createVerticalStrut(10));
		
		aniadirTarea.addActionListener(new AccionAniadir());
		
		
		
		//--------------------------------------------------------
		
		//añadimos el boton buscar tareas a la caja inicial
		
		JButton buscarTarea=new JButton("Buscar tarea");
		
		buscarTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cajaInicio.add(buscarTarea);
		
		buscarTarea.addActionListener(new AccionBuscar());
		
		
		//--------------------------------------------------------
		
		Box cajaCentral=Box.createHorizontalBox();//creamos una caja horizontal a la que añadiremos nuestra caja vertical para posteriormente colorcarla en el centro de nuestra lamina
		
		cajaCentral.add(cajaInicio);
		
		add(cajaCentral,BorderLayout.CENTER);
		
		
	}
	
	//Accion del boton añadir
	
	private class AccionAniadir implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			abrirNuevoMarco(new LaminaAniadir(laminaBuscador),"Añadir tarea");
			
		}
		
	}
	
	
	// Accion del boton buscador
	private class AccionBuscar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			abrirNuevoMarco(laminaBuscador,"Buscador de tareas");
			
		}
		
	}
	
	public void abrirNuevoMarco(JPanel lamina,String titulo) {
		
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
	
	
}

class LaminaBuscador extends JPanel{
	
	private JComboBox<Tarea> comboBuscador;
	
	
	public LaminaBuscador() {
		
		setLayout(new BorderLayout());
		
		JPanel laminaCombo=new JPanel();
		
		comboBuscador=new JComboBox();
		
		comboBuscador.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		comboBuscador.setEditable(true);
		
		laminaCombo.add(comboBuscador);

		add(laminaCombo,BorderLayout.CENTER);
		
		
		
	}
	
	public void aniadirTarea(Tarea t) {
		
		comboBuscador.addItem(t);
	}
}

class LaminaAniadir extends JPanel{
	
	Font miFuente=new Font("Arial",Font.BOLD,30);
	
	private JTextField cuadroTarea;
	
	private JComboBox comboTipo;
	
	private JTextArea texto1;
	
	private LaminaBuscador laminaBuscador;
	
	
	public LaminaAniadir(LaminaBuscador laminaBuscador) {
		
		this.laminaBuscador=laminaBuscador;
		
		setLayout(new GridLayout(3,1));
		
		JPanel panel1=new JPanel();
		
		JPanel panel2=new JPanel();
		
		JPanel panel3=new JPanel();
		
		
		//---------------------------------------------------
		
		Box cajaTarea=Box.createVerticalBox();
		
		JLabel tarea=new JLabel("Tarea");
		
		tarea.setFont(miFuente);
		
		tarea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cajaTarea.add(tarea);
		
		cajaTarea.add(Box.createVerticalStrut(25));
		
		cuadroTarea=new JTextField(20);
		
		cuadroTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cajaTarea.add(cuadroTarea);
		
		panel1.add(cajaTarea);
		
		//------------------------------------------------------
		
		Box cajaTipo=Box.createVerticalBox();
		
		JLabel tipoTarea=new JLabel("Tipo de Tarea");
		
		tipoTarea.setFont(miFuente);
		
		tipoTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cajaTipo.add(tipoTarea);
		
		cajaTipo.add(Box.createVerticalStrut(25));
		
		comboTipo=new JComboBox();
		
		comboTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		comboTipo.addItem("Importante");
		
		comboTipo.addItem("Aplazable");
		
		comboTipo.addItem("Opcional");
		
		cajaTipo.add(comboTipo);
		
		panel2.add(cajaTipo);
		
		//------------------------------------------------------
		
		Box cajaDesc=Box.createVerticalBox();
		
		JLabel descripcion=new JLabel("Descripcion");
		
		descripcion.setFont(miFuente);
		
		descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cajaDesc.add(descripcion);
		
		cajaDesc.add(Box.createVerticalStrut(15));
		
		texto1=new JTextArea(5,35);
		
		cajaDesc.add(new JScrollPane(texto1));
		
		
		
		JButton aniadir=new JButton("Añadir");
		
		cajaDesc.add(aniadir);
		
		aniadir.addActionListener(new AccionBotonAniadir());
		
		aniadir.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel3.add(cajaDesc);
		
		//--------------------------------------------------------
		
		
		add(panel1);
		
		add(panel2);
		
		add(panel3);
		
	
		
		
	}
	
	private class AccionBotonAniadir implements ActionListener{
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			 int indice=comboTipo.getSelectedIndex();
			
			 String nombre=cuadroTarea.getText();
			
			 String importancia=(String) comboTipo.getItemAt(indice);
			
			 String descripcion=texto1.getText();
			
			Tarea tarea=new Tarea(nombre,importancia,descripcion);
			
			laminaBuscador.aniadirTarea(tarea);
			
			cuadroTarea.setText("");
			
			texto1.setText("");
			
		}
		
		
	}
	

}




