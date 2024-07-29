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
	
	public MarcoTarea(){
	
	Toolkit miPantalla=Toolkit.getDefaultToolkit();
	
	Dimension tamano=miPantalla.getScreenSize();
	
	int ancho=tamano.width;
	
	int alto=tamano.height;
	
	setBounds(ancho/4,alto/4,ancho/2,alto/2);
	
	setTitle("Gestor de tareas");
	
	add(new LaminaTarea());
	
	
	
	}
	
}

class LaminaTarea extends JPanel{
	
	public LaminaTarea() {
		
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
			
			abrirNuevoMarco(new LaminaAniadir(),"Añadir tarea");
			
		}
		
	}
	
	
	// Accion del boton buscador
	private class AccionBuscar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			abrirNuevoMarco(new LaminaBuscador(),"Buscador de tareas");
			
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
	
	public LaminaBuscador() {
		
		setLayout(new BorderLayout());
		
		JPanel laminaCombo=new JPanel();
		
		JComboBox comboBuscador=new JComboBox();
		
		comboBuscador.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		comboBuscador.setEditable(true);
		
		laminaCombo.add(comboBuscador);

		add(laminaCombo,BorderLayout.CENTER);
		
		
		
	}
}

class LaminaAniadir extends JPanel{
	
	Font miFuente=new Font("Arial",Font.BOLD,30);
	
	public LaminaAniadir() {
		
		setLayout(new GridLayout(4,1));
		
		JPanel panel1=new JPanel();
		
		JPanel panel2=new JPanel();
		
		JPanel panel3=new JPanel();
		
		JPanel panel4=new JPanel();
		
		//---------------------------------------------------
		
		Box cajaTarea=Box.createVerticalBox();
		
		JLabel tarea=new JLabel("Tarea");
		
		tarea.setFont(miFuente);
		
		tarea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cajaTarea.add(tarea);
		
		cajaTarea.add(Box.createVerticalStrut(25));
		
		JTextField cuadroTarea=new JTextField(20);
		
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
		
		JComboBox comboTipo=new JComboBox();
		
		comboTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		comboTipo.addItem("Muy importante");
		
		comboTipo.addItem("Aplazabel");
		
		comboTipo.addItem("Opcional");
		
		cajaTipo.add(comboTipo);
		
		panel2.add(cajaTipo);
		
		//------------------------------------------------------
		
		Box cajaDesc=Box.createVerticalBox();
		
		JLabel descripcion=new JLabel("Descripcion");
		
		descripcion.setFont(miFuente);
		
		descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		cajaDesc.add(descripcion);
		
		cajaDesc.add(Box.createVerticalStrut(25));
		
		JTextArea texto1=new JTextArea(8,30);
		
		cajaDesc.add(new JScrollPane(texto1));
		
		panel3.add(cajaDesc);
		
		//--------------------------------------------------------
		
		JButton aniadir=new JButton("Añadir");
		
		aniadir.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panel4.add(aniadir);
		
		add(panel1);
		
		add(panel2);
		
		add(panel3);
		
		add(panel4);
		
		
	}
	
}




