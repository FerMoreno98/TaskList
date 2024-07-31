package com.List;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

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
	//instanciamos la lamina buscador para poder pasarselo por parametro
	laminaBuscador=new LaminaBuscador();
	
	add(new LaminaTarea(laminaBuscador));
	
	}
	
	
	
	
	
}

class LaminaTarea extends JPanel{
	
	private LaminaBuscador laminaBuscador;
	
	//lo primero, le pasamos por parametro la Lamina buscador al constructor de la lamina tarea
	public LaminaTarea(LaminaBuscador laminaBuscador) {
		//le decimos al constructor que la lamina del buscador que le pasamos por parametro es la misma que la que hay en la clase lamina buscador
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
			
			ClaseUtilidades.abrirNuevoMarco(new LaminaAniadir(laminaBuscador),"Añadir tarea");
			
		}
		
	}
	
	
	// Accion del boton buscador
	private class AccionBuscar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			ClaseUtilidades.abrirNuevoMarco(laminaBuscador,"Buscador de tareas");
			
		}
		
	}
	

	
	
}

class LaminaBuscador extends JPanel{
	
	private JComboBox<Tarea> comboBuscador;
	
	
	public LaminaBuscador() {
		
		setLayout(new BorderLayout());
		
		Box cajaBuscador=Box.createVerticalBox();
		
		JPanel contenedor=new JPanel(new GridBagLayout());
		
		comboBuscador=new JComboBox();
		
		comboBuscador.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		comboBuscador.setMaximumSize(new Dimension(200,35));
		
		cajaBuscador.add(comboBuscador);
	
		cajaBuscador.add(Box.createVerticalStrut(20));
		
		JButton eliminarTarea=new JButton("Eliminar tarea");
		
		eliminarTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		eliminarTarea.addActionListener(new AccionBotonEliminar());
		
		cajaBuscador.add(eliminarTarea);
		
		cajaBuscador.add(Box.createVerticalStrut(20));
		
		JButton editarTarea=new JButton("Editar tarea");
		
		editarTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		editarTarea.addActionListener(new BotonAccionEditar());
		
		cajaBuscador.add(editarTarea);
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		gbc.gridx=0;
		
		gbc.gridy=0;
		
		gbc.anchor=GridBagConstraints.CENTER;
		
		contenedor.add(cajaBuscador, gbc);
		
		add(contenedor,BorderLayout.CENTER);
		
		aniadirElementos();
		
		
		
	}
	//añadimos el metodo aniadir tarea
	public void aniadirTarea(Tarea t) {
		
		comboBuscador.addItem(t);
	}
	
	//--------------------------------------------------------------------------- Funcionalidad base de datos del boton añadir para guardar en el buscador las tareas
	
	public void aniadirElementos() {
	
	try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	}catch(Exception r) {
		
		System.out.println("no se conecta");
		
		System.exit(1);
		
	}
	
	String sql="select * from tablaTareas";
	
	try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tareas","root","");
			PreparedStatement ps=con.prepareStatement(sql)){
		
	
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			
			Tarea tareas=new Tarea(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
			
			comboBuscador.addItem(tareas);
			
		}
			
		
	}catch(Exception t) {
		
		t.printStackTrace();
		
	}
	
	//--------------------------------------------------------------- Accion del Boton eliminar
	}
	
	private class AccionBotonEliminar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Tarea tareaSeleccionada=(Tarea) comboBuscador.getSelectedItem();
			
			if(tareaSeleccionada!=null) {
			
			String tareaNombre=tareaSeleccionada.getNombre();
		
			
			String sql="delete from tablaTareas where Nombre=?";
			
			try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tareas","root","");
					PreparedStatement ps=con.prepareStatement(sql)){
				
				ps.setString(1,tareaNombre);
				
				int r=ps.executeUpdate();
					
				
			}catch(Exception t) {
				
				t.printStackTrace();
				
			}
			
			comboBuscador.setSelectedItem(null);
			
		}else{
			JOptionPane.showMessageDialog(LaminaBuscador.this,"No has seleccionado ninguna tarea");
		}
			}
		
	}
	
	private class BotonAccionEditar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			LaminaBuscador l=new LaminaBuscador();
			
			
			ClaseUtilidades.abrirNuevoMarco(new LaminaEditar(l), "Editar tarea");
			
		}
		
			// TODO Auto-generated method stub
			
			class LaminaEditar extends JPanel{
				
				Font miFuente=new Font("Arial",Font.BOLD,25);
				
				private JTextField cuadroTarea;
				
				private JComboBox comboTipo;
				
				private JTextArea texto1;
				
				private LaminaBuscador laminaBuscador;
				
				private Tarea tareax=(Tarea)comboBuscador.getSelectedItem();
				
				//hacemos lo mismo con el metodo laminaAniadir, le pasamos por parametro laminaBuscador y le decimos que la lamina buscador que hay aqui es la misma que la de la clase lamina buscador
				public LaminaEditar(LaminaBuscador laminaBuscador) {
					
					this.laminaBuscador=laminaBuscador;
					
					setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
					
					add(createLaminaTarea());
					
					add(createLaminaTipo());
					
					add(createLaminaDesc());
					
					
					
					
					
					
				}
					
					private JPanel createLaminaTarea() {
						//creamos un panel y le ponemos un flowLayout
						JPanel panel=new JPanel();
						
						panel.setLayout(new FlowLayout(FlowLayout.CENTER));
						
						
						//creamos una caja que insertaremos posteriormente en el panel
						Box cajaTarea=Box.createVerticalBox();
						
						//creamos un label
						JLabel tarea=new JLabel("Tarea");
						
						tarea.setFont(miFuente);
						
						tarea.setAlignmentX(Component.CENTER_ALIGNMENT);
						
						cajaTarea.add(tarea);
						
						
						//añadimos un espacio entre los elementos de la caja
						cajaTarea.add(Box.createVerticalStrut(25));
						
						
						//creamos un cuadro
						cuadroTarea=new JTextField(20);
						
						cuadroTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
						
						cajaTarea.add(cuadroTarea);
						
						cuadroTarea.setText(tareax.getNombre());
						
						
						//añadimos la caja al panel
						panel.add(cajaTarea);
						
						return panel;
						
						
					}
					
					private JPanel createLaminaTipo() {
						
						JPanel panel=new JPanel();
						
						panel.setLayout(new FlowLayout(FlowLayout.CENTER));
						
						
						
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
						
						comboTipo.setSelectedItem(tareax.getImportancia());
						
						cajaTipo.add(comboTipo);
						
						
						
						
						panel.add(cajaTipo);
						
						return panel;
						
						
					}
					
					private JPanel createLaminaDesc() {
						
						JPanel panel=new JPanel();
						
						panel.setLayout(new FlowLayout(FlowLayout.CENTER));
						
						
						
						Box cajaDesc=Box.createVerticalBox();
						
						
						
						JLabel descripcion=new JLabel("Descripcion");
						
						descripcion.setFont(miFuente);
						
						descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
						
						cajaDesc.add(descripcion);
						
						
						
						cajaDesc.add(Box.createVerticalStrut(15));
						
						
						
						texto1=new JTextArea(5,35);
						
						cajaDesc.add(new JScrollPane(texto1));
						
						texto1.setText(tareax.getDescripcion());
						
						
						
						JButton aniadir=new JButton("Editar");
						
						cajaDesc.add(Box.createVerticalStrut(15));
						
						cajaDesc.add(aniadir);
						
						aniadir.addActionListener(new AccionBotonCambiar());
						
						aniadir.setAlignmentX(Component.CENTER_ALIGNMENT);
						
						
						
						panel.add(cajaDesc);
						
						return panel;
						
						
					}
					
					
					class AccionBotonCambiar implements ActionListener{

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							Tarea tareaSeleccionada=(Tarea) comboBuscador.getSelectedItem();
							
							if(tareaSeleccionada!=null) {
								
							String nuevoNombre=cuadroTarea.getText();
								
							String nuevaImpor=(String)comboTipo.getSelectedItem();
							
							String nuevaDesc=texto1.getText();
							
							int id=tareaSeleccionada.getId();
					
						
							
							String sql="update tablaTareas set Nombre=?,Importancia=?,Descripcion=? where id=?";
							
							try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tareas","root","");
									PreparedStatement ps=con.prepareStatement(sql)){
								ps.setString(1, nuevoNombre);
								ps.setString(2, nuevaImpor);
								ps.setString(3, nuevaDesc);
								ps.setInt(4, id);
							
								
								int r=ps.executeUpdate();
									
								
							}catch(Exception t) {
								
								t.printStackTrace();
								
							}
							
							JOptionPane.showMessageDialog(LaminaBuscador.this, "Se ha editado su tarea correctamente");
							
						}else{
							JOptionPane.showMessageDialog(LaminaBuscador.this,"No has seleccionado ninguna tarea");
						}
							}
						
					}
			
		
		}
			
		
		
		
	}
	
	
}

class LaminaAniadir extends JPanel{
	
	Font miFuente=new Font("Arial",Font.BOLD,25);
	
	private JTextField cuadroTarea;
	
	private JComboBox comboTipo;
	
	private JTextArea texto1;
	
	private LaminaBuscador laminaBuscador;
	
	//hacemos lo mismo con el metodo laminaAniadir, le pasamos por parametro laminaBuscador y le decimos que la lamina buscador que hay aqui es la misma que la de la clase lamina buscador
	public LaminaAniadir(LaminaBuscador laminaBuscador) {
		
		this.laminaBuscador=laminaBuscador;
		
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		add(createLaminaTarea());
		
		add(createLaminaTipo());
		
		add(createLaminaDesc());
		
		
	}
		
		private JPanel createLaminaTarea() {
			//creamos un panel y le ponemos un flowLayout
			JPanel panel=new JPanel();
			
			panel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			
			//creamos una caja que insertaremos posteriormente en el panel
			Box cajaTarea=Box.createVerticalBox();
			
			//creamos un label
			JLabel tarea=new JLabel("Tarea");
			
			tarea.setFont(miFuente);
			
			tarea.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			cajaTarea.add(tarea);
			
			
			//añadimos un espacio entre los elementos de la caja
			cajaTarea.add(Box.createVerticalStrut(25));
			
			
			//creamos un cuadro
			cuadroTarea=new JTextField(20);
			
			cuadroTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			cajaTarea.add(cuadroTarea);
			
			
			//añadimos la caja al panel
			panel.add(cajaTarea);
			
			return panel;
			
			
		}
		
		private JPanel createLaminaTipo() {
			
			JPanel panel=new JPanel();
			
			panel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			
			
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
			
			
			
			
			panel.add(cajaTipo);
			
			return panel;
			
			
		}
		
		private JPanel createLaminaDesc() {
			
			JPanel panel=new JPanel();
			
			panel.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			
			
			Box cajaDesc=Box.createVerticalBox();
			
			
			
			JLabel descripcion=new JLabel("Descripcion");
			
			descripcion.setFont(miFuente);
			
			descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			cajaDesc.add(descripcion);
			
			
			
			cajaDesc.add(Box.createVerticalStrut(15));
			
			
			
			texto1=new JTextArea(5,35);
			
			cajaDesc.add(new JScrollPane(texto1));
			
			
			
			JButton aniadir=new JButton("Añadir");
			
			cajaDesc.add(Box.createVerticalStrut(15));
			
			cajaDesc.add(aniadir);
			
			aniadir.addActionListener(new AccionBotonAniadir());
			
			aniadir.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			
			
			panel.add(cajaDesc);
			
			return panel;
			
			
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
			
			//-----------------------------------------------
			
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				
			}catch(Exception r) {
				
				System.out.println("no se conecta");
				
				System.exit(1);
				
			}
			
			String sql="insert into tablaTareas (Nombre,Importancia,Descripcion) values(?,?,?);";
			
			try(Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tareas","root","");
					PreparedStatement ps=con.prepareStatement(sql)){
				
				ps.setString(1,nombre);
				
				ps.setString(2, importancia);
				
				ps.setString(3, descripcion);
				
				int r=ps.executeUpdate();
					
				
			}catch(Exception t) {
				
				t.printStackTrace();
				
			}
			
		}
		
		
	}
	}
	






