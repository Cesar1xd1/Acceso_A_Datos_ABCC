package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import controlador.AlumnoDAO;
import jdk.tools.jimage.resources.jimage;
import modelo.Alumno;








class VentanaAltas extends JFrame implements ActionListener{
	JPanel verde = new JPanel();
	JLabel titulo = new JLabel("Altas Alumnos");
	JLabel lNControl = new JLabel("NUMERO DE CONTROL:");
	JTextField tNControl = new JTextField();
	JLabel lNombre = new JLabel("NOMBRE:");
	JTextField tNombre = new JTextField();
	JLabel lPApellido = new JLabel("APELLIDO PATERNO:");
	JTextField tPApellido = new JTextField();
	JLabel lSApellido = new JLabel("APELLIDO MATERNO:");
	JTextField tSApellido = new JTextField();
	JLabel lSemestre = new JLabel("SEMESTRE:");
	JComboBox cSemestre = new JComboBox();
	JLabel lCarrera = new JLabel("CARRERA:");
	JComboBox cCarrera = new JComboBox();
	JButton bAgregar = new JButton("AGREGAR");
	JButton bBorrar = new JButton("BORRAR");
	JButton bCancelar = new JButton("CANCELAR");
	JTable tabla = new JTable();
	
	public void atuaclizaTabla(JTable tabla) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
			String Consulta = "SELECT * FROM alumnos";
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tabla.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
	}
	
	
	
	
	
	
	public VentanaAltas() {
		getContentPane().setLayout(null);
		setSize(700,500);
		setLocationRelativeTo(null);
		setTitle("ALTAS ALUMNOS");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
		String Consulta = "SELECT * FROM alumnos";
		
		ResultSetTableModel modeloDatos = null;
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane escroll = new JScrollPane(tabla);
		tabla.setModel(modeloDatos);
		
	
		setVisible(true);
		
		verde.setBackground(new Color(0, 255, 0));
		verde.setLayout(null);
		verde.setBounds(0, 0, 700, 70);
		add(verde);
		
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(20, 20, 200, 20);
		verde.add(titulo);
		
		lNControl.setBounds(100,100, 180, 20);
		add(lNControl);
		tNControl.setBounds(250,100, 200, 20);
		add(tNControl);
		
		
		lNombre.setBounds(100,130,80, 20);
		add(lNombre);
		tNombre.setBounds(170,130, 280, 20);
		add(tNombre);
		
		
		lPApellido.setBounds(100,160, 180, 20);
		add(lPApellido);
		tPApellido.setBounds(240,160, 210, 20);
		add(tPApellido);
		
		
		lSApellido.setBounds(100,190, 180, 20);
		add(lSApellido);
		tSApellido.setBounds(240,190, 210, 20);
		add(tSApellido);
		
		
		
		lSemestre.setBounds(100,220, 180, 20);
		add(lSemestre);
		cSemestre.setBounds(240,220, 210, 20);
		cSemestre.addItem("0");
		cSemestre.addItem("1");
		cSemestre.addItem("2");
		cSemestre.addItem("3");
		cSemestre.addItem("4");
		cSemestre.addItem("5");
		cSemestre.addItem("6");
		add(cSemestre);
		
		lCarrera.setBounds(100,250, 180, 20);
		add(lCarrera);
		cCarrera.setBounds(240,250, 210, 20);
		cCarrera.addItem("0");
		cCarrera.addItem("ISC");
		cCarrera.addItem("IM");
		cCarrera.addItem("CP");
		cCarrera.addItem("IA");
		cCarrera.addItem("LA");
		add(cCarrera);
		
		bAgregar.setBounds(500,120 , 100, 20);
		bAgregar.addActionListener(this);
		add(bAgregar);
		
		bBorrar.setBounds(500,170 , 100, 20);
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		bCancelar.setBounds(500,220 , 100, 20);
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		escroll.setBounds(90, 320, 500, 100);
		add(escroll);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bAgregar) {
			String nControl = tNControl.getText();
			String nombre = tNombre.getText();
			String pApellido = tPApellido.getText();
			String sApellido= tSApellido.getText();
			String semestre = (String) cSemestre.getSelectedItem();
			String carrera = (String) cCarrera.getSelectedItem();
			int isemestre = Integer.valueOf(semestre);
			byte bsemestre = (byte)isemestre;
			//Se insertara siempre una edad de 0 
			Alumno a = new Alumno(nControl,nombre,pApellido,sApellido,(byte)0,bsemestre,carrera);
			AlumnoDAO aDAO = new AlumnoDAO();
			aDAO.insertarRegistro(a);
			atuaclizaTabla(tabla);
		}else if(e.getSource()==bBorrar) {
			tNControl.setText("");
			tNombre.setText("");
			tPApellido.setText("");
			tSApellido.setText("");
			cSemestre.setSelectedIndex(0);
			cCarrera.setSelectedIndex(0);
		}else if(e.getSource()==bCancelar) {
			//Se uso setVisible porqie dispose, termina la ejecucucion y esta pensado para ser
			//Varias ventanas distintas
			setVisible(false);
			//dispose();
		}
		
		
		
		
	}

	
}//VENTANA-ALTAS

class VentanaBajas extends JFrame implements ActionListener{
	JPanel rojo = new JPanel();
	JLabel titulo = new JLabel("Bajas Alumnos");
	JLabel lNControl = new JLabel("NUMERO DE CONTROL:");
	JTextField tNControl = new JTextField();
	JLabel lNombre = new JLabel("NOMBRE:");
	JTextField tNombre = new JTextField();
	JLabel lPApellido = new JLabel("APELLIDO PATERNO:");
	JTextField tPApellido = new JTextField();
	JLabel lSApellido = new JLabel("APELLIDO MATERNO:");
	JTextField tSApellido = new JTextField();
	JLabel lSemestre = new JLabel("SEMESTRE:");
	JComboBox cSemestre = new JComboBox();
	JLabel lCarrera = new JLabel("CARRERA:");
	JComboBox cCarrera = new JComboBox();
	JButton bEliminar = new JButton("ELIMINAR");
	
	
	JButton bBuscar = new JButton();
	ImageIcon iconoBuscar = new ImageIcon("src/recursos/buscar.png");
	
	JButton bBorrar = new JButton("BORRAR");
	JButton bCancelar = new JButton("CANCELAR");
	JTable tabla = new JTable();
	
	public void obtenerRegistroTabla() {
		tNControl.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 0));
		tNombre.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
		tPApellido.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 2));
		tSApellido.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 3));
		cSemestre.setSelectedItem(tabla.getValueAt(tabla.getSelectedRow(), 5));
		cCarrera.setSelectedItem((String) tabla.getValueAt(tabla.getSelectedRow(), 6));
		
		
	}
	
	public void atuaclizaTabla(String sql) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
			
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, sql);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tabla.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
		
	}
	
	public VentanaBajas() {
		getContentPane().setLayout(null);
		setSize(700,500);
		setLocationRelativeTo(null);
		setTitle("BAJAS ALUMNOS");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
		String Consulta = "SELECT * FROM alumnos";
		
		ResultSetTableModel modeloDatos = null;
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane escroll = new JScrollPane(tabla);
		tabla.setModel(modeloDatos);
		
		
		
		
		
		setVisible(true);
		
		rojo.setBackground(new Color(255, 0, 0));
		rojo.setLayout(null);
		rojo.setBounds(0, 0, 700, 70);
		add(rojo);
		
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(20, 20, 250, 25);
		rojo.add(titulo);
		
		lNControl.setBounds(50,100, 180, 20);
		lNControl.setFont(new Font("Arial Black", Font.PLAIN, 12));
		add(lNControl);
		tNControl.setBounds(220,90, 150, 40);
		tNControl.setBackground(new Color(0,255,255));
		add(tNControl);
		
		
		lNombre.setBounds(100,180,80, 20);
		add(lNombre);
		tNombre.setBounds(170,180, 280, 20);
		tNombre.setEnabled(false);
		add(tNombre);
		
		
		lPApellido.setBounds(100,210, 180, 20);
		add(lPApellido);
		tPApellido.setBounds(240,210, 210, 20);
		tPApellido.setEnabled(false);
		add(tPApellido);
		
		
		lSApellido.setBounds(100,240, 180, 20);
		add(lSApellido);
		tSApellido.setBounds(240,240, 210, 20);
		tSApellido.setEnabled(false);
		add(tSApellido);
		
		
		
		lSemestre.setBounds(100,270, 180, 20);
		add(lSemestre);
		cSemestre.setBounds(240,270, 210, 20);
		cSemestre.addItem("0");
		cSemestre.addItem("1");
		cSemestre.addItem("2");
		cSemestre.addItem("3");
		cSemestre.addItem("4");
		cSemestre.addItem("5");
		cSemestre.addItem("6");
		cSemestre.setEnabled(false);
		add(cSemestre);
		
		lCarrera.setBounds(100,300, 180, 20);
		add(lCarrera);
		cCarrera.setBounds(240,300, 210, 20);
		cCarrera.addItem("0");
		cCarrera.addItem("ISC");
		cCarrera.addItem("IM");
		cCarrera.addItem("CP");
		cCarrera.addItem("IA");
		cCarrera.addItem("LA");
		cCarrera.setEnabled(false);
		add(cCarrera);
		
		bBuscar.setBounds(400,90 , 100, 35);
		bBuscar.addActionListener(this);
		bBuscar.setIcon(iconoBuscar);
		add(bBuscar);
		
		bBorrar.setBounds(520, 100 , 100, 20);
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		
		bEliminar.setBounds(500, 200, 100, 20);
		bEliminar.addActionListener(this);
		add(bEliminar);
		
		bCancelar.setBounds(500,270 , 100, 20);
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		escroll.setBounds(90, 350, 500, 100);
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {@Override public void mouseClicked(java.awt.event.MouseEvent evt) { obtenerRegistroTabla();}         });
		add(escroll);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
			
			String sql = "SELECT * FROM alumnos ";
			boolean primero=true;
				if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
				primero=false;
				sql+=("NumControl = '"+tNControl.getText()+"'");
			
				atuaclizaTabla(sql);
				
			
		}else if(e.getSource()==bBorrar) {
			tNControl.setText("");
			tNombre.setText("");
			tPApellido.setText("");
			tSApellido.setText("");
			cSemestre.setSelectedIndex(0);
			cCarrera.setSelectedIndex(0);
		}else if(e.getSource()==bCancelar) {
			setVisible(false);
		}else if(e.getSource()==bEliminar) {
			String nC = tNControl.getText();
			AlumnoDAO aDAO = new AlumnoDAO();
			aDAO.eliminarRegistro(nC);
			atuaclizaTabla("SELECT * FROM alumnos");
			tNControl.setText("");
			tNombre.setText("");
			tPApellido.setText("");
			tSApellido.setText("");
			cSemestre.setSelectedIndex(0);
			cCarrera.setSelectedIndex(0);
		}

	
	}	
	
}//VENTANA-BAJAS

class VentanaCambios extends JFrame implements ActionListener{
	JPanel naranja = new JPanel();
	JLabel titulo = new JLabel("Modificaciones Alumnos");
	JLabel lNControl = new JLabel("NUMERO DE CONTROL:");
	JTextField tNControl = new JTextField();
	JLabel lNombre = new JLabel("NOMBRE:");
	JTextField tNombre = new JTextField();
	JLabel lPApellido = new JLabel("APELLIDO PATERNO:");
	JTextField tPApellido = new JTextField();
	JLabel lSApellido = new JLabel("APELLIDO MATERNO:");
	JTextField tSApellido = new JTextField();
	JLabel lSemestre = new JLabel("SEMESTRE:");
	JComboBox cSemestre = new JComboBox();
	JLabel lCarrera = new JLabel("CARRERA:");
	JComboBox cCarrera = new JComboBox();
	JButton bCambios = new JButton("GUARDAR CAMBIOS");
	JTable tabla = new JTable();
	
	JButton bBuscar = new JButton();
	ImageIcon iconoBuscar = new ImageIcon("src/recursos/buscar.png");
	
	JButton bBorrar = new JButton("BORRAR");
	JButton bCancelar = new JButton("CANCELAR");
	
	
	public void obtenerRegistroTabla() {
		tNControl.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 0));
		tNombre.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 1));
		tPApellido.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 2));
		tSApellido.setText((String) tabla.getValueAt(tabla.getSelectedRow(), 3));
		cSemestre.setSelectedItem(tabla.getValueAt(tabla.getSelectedRow(), 5));
		cCarrera.setSelectedItem((String) tabla.getValueAt(tabla.getSelectedRow(), 6));
		
		
	}
	
	public void atuaclizaTabla(String sql) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
			
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, sql);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tabla.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
		
	}

	
	
	
	
	public VentanaCambios() {
		getContentPane().setLayout(null);
		setSize(700,500);
		setLocationRelativeTo(null);
		setTitle("CAMBIOS ALUMNOS");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
		String Consulta = "SELECT * FROM alumnos";
		
		ResultSetTableModel modeloDatos = null;
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane escroll = new JScrollPane(tabla);
		tabla.setModel(modeloDatos);
		
		
		
		
		setVisible(true);
		
		naranja.setBackground(new Color(255, 200, 0));
		naranja.setLayout(null);
		naranja.setBounds(0, 0, 700, 70);
		add(naranja);
		
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(20, 20, 350, 25);
		naranja.add(titulo);
		
		lNControl.setBounds(50,100, 180, 20);
		lNControl.setFont(new Font("Arial Black", Font.PLAIN, 12));
		add(lNControl);
		tNControl.setBounds(220,90, 150, 40);
		tNControl.setBackground(new Color(0,255,255));
		add(tNControl);
		
		
		lNombre.setBounds(100,180,80, 20);
		add(lNombre);
		tNombre.setBounds(170,180, 280, 20);
		add(tNombre);
		
		
		lPApellido.setBounds(100,210, 180, 20);
		add(lPApellido);
		tPApellido.setBounds(240,210, 210, 20);
		add(tPApellido);
		
		
		lSApellido.setBounds(100,240, 180, 20);
		add(lSApellido);
		tSApellido.setBounds(240,240, 210, 20);
		add(tSApellido);
		
		
		
		lSemestre.setBounds(100,270, 180, 20);
		add(lSemestre);
		cSemestre.setBounds(240,270, 210, 20);
		cSemestre.addItem("0");
		cSemestre.addItem("1");
		cSemestre.addItem("2");
		cSemestre.addItem("3");
		cSemestre.addItem("4");
		cSemestre.addItem("5");
		cSemestre.addItem("6");
		add(cSemestre);
		
		lCarrera.setBounds(100,300, 180, 20);
		add(lCarrera);
		cCarrera.setBounds(240,300, 210, 20);
		cCarrera.addItem("0");
		cCarrera.addItem("ISC");
		cCarrera.addItem("IM");
		cCarrera.addItem("CP");
		cCarrera.addItem("IA");
		cCarrera.addItem("LA");
		add(cCarrera);
		
		bBuscar.setBounds(400,90 , 100, 35);
		bBuscar.addActionListener(this);
		bBuscar.setIcon(iconoBuscar);
		add(bBuscar);
		
		bBorrar.setBounds(520, 100 , 100, 20);
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		
		bCambios.setBounds(480, 200, 170, 20);
		bCambios.addActionListener(this);
		add(bCambios);
		
		bCancelar.setBounds(500,270 , 100, 20);
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		
		tabla.addMouseListener(new java.awt.event.MouseAdapter() {@Override public void mouseClicked(java.awt.event.MouseEvent evt) { obtenerRegistroTabla();}         });
		escroll.setBounds(90, 350, 500, 100);
		add(escroll);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
			String sql = "SELECT * FROM alumnos ";
			boolean primero=true;
				if (!primero) {sql+=" AND ";}else {sql+="WHERE ";}
				primero=false;
				sql+=("NumControl = '"+tNControl.getText()+"'");
			
				atuaclizaTabla(sql);
				
			
			
			
			
		}else if(e.getSource()==bBorrar) {
			tNControl.setText("");
			tNombre.setText("");
			tPApellido.setText("");
			tSApellido.setText("");
			cSemestre.setSelectedIndex(0);
			cCarrera.setSelectedIndex(0);
		}else if(e.getSource()==bCancelar) {
			setVisible(false);
		}else if(e.getSource()==bCambios) {
			String nC = tNControl.getText();
			AlumnoDAO aDAO = new AlumnoDAO();
			String nombre = tNombre.getText();
			String pAp = tPApellido.getText();
			String sAp = tSApellido.getText();
			String semestre = (String) cSemestre.getSelectedItem();
			String carrera = (String) cCarrera.getSelectedItem();
			int isemestre = Integer.valueOf(semestre);
			byte bsemestre = (byte)isemestre;
			
			Alumno a = new Alumno(nC,nombre,pAp,sAp,(byte)21,bsemestre,carrera);
			aDAO.modificarRegistro(a);
			atuaclizaTabla("SELECT * FROM alumnos");
		
			
		}

	
	}	
	
}//VENTANA-CAMBIOS

class VentanaConsultas extends JFrame implements ActionListener{
	JPanel azul = new JPanel();
	JLabel titulo = new JLabel("Consultas Alumnos");
	JLabel lNControl = new JLabel("NUMERO DE CONTROL:");
	JTextField tNControl = new JTextField();
	JLabel lNombre = new JLabel("NOMBRE:");
	JTextField tNombre = new JTextField();
	JLabel lPApellido = new JLabel("APELLIDO PATERNO:");
	JTextField tPApellido = new JTextField();
	JLabel lSApellido = new JLabel("APELLIDO MATERNO:");
	JTextField tSApellido = new JTextField();
	JLabel lSemestre = new JLabel("SEMESTRE:");
	JComboBox cSemestre = new JComboBox();
	JLabel lCarrera = new JLabel("CARRERA:");
	JComboBox cCarrera = new JComboBox();
	JButton bBuscar = new JButton();
	JButton bBorrar = new JButton("BORRAR");
	JButton bCancelar = new JButton("CANCELAR");
	JTable tabla = new JTable();
	ImageIcon iconoBuscar = new ImageIcon("src/recursos/buscar.png");
	JRadioButton rbNControl = new JRadioButton();
	JRadioButton rbNombre = new JRadioButton();
	JRadioButton rbPApellido = new JRadioButton();
	JRadioButton rbSApellido = new JRadioButton();
	JRadioButton rbSemestre = new JRadioButton();
	JRadioButton rbCarrera = new JRadioButton();
	JRadioButton rbTodos = new JRadioButton("TODOS");
	JLabel radioB = new JLabel("Seleccione un filtro");
	
	
	ButtonGroup bGrupo = new ButtonGroup();
	
	public void atuaclizaTabla(String sql) {
		try {
			String controlador = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
			
			
			ResultSetTableModel modeloDatos = null;
			try {
				modeloDatos = new ResultSetTableModel(controlador, url, sql);
			}catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(getContentPane(), ex);
			}
			tabla.setModel(modeloDatos);
		}//Try
		catch (Exception sqle) {
			JOptionPane.showMessageDialog(getContentPane(), sqle);
		}
	}
	
	
	public void blanquear() {
		tNControl.setText("");
		tNombre.setText("");
		tPApellido.setText("");
		tSApellido.setText("");
		cSemestre.setSelectedIndex(0);
		cCarrera.setSelectedIndex(0);
	}
	
	
	
	public VentanaConsultas() {
		getContentPane().setLayout(null);
		setSize(700,500);
		setLocationRelativeTo(null);
		setTitle("CONSULTAS ALUMNOS");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		
		String controlador = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Escuela_Topicos";
		String Consulta = "SELECT * FROM alumnos";
		
		ResultSetTableModel modeloDatos = null;
		try {
			modeloDatos = new ResultSetTableModel(controlador, url, Consulta);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		JScrollPane escroll = new JScrollPane(tabla);
		tabla.setModel(modeloDatos);
		
	
		setVisible(true);
		
		azul.setBackground(new Color(0, 0, 255));
		azul.setLayout(null);
		azul.setBounds(0, 0, 700, 70);
		add(azul);
		
		titulo.setFont(new Font("Arial Black", Font.PLAIN, 25));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(20, 20, 300, 20);
		azul.add(titulo);
		
		rbTodos.setSelected(true);
		
		
		
		
		radioB.setBounds(100, 75, 150, 20);
		add(radioB);
		
		rbTodos.setBounds(50, 100, 100, 20);
		bGrupo.add(rbTodos);
		add(rbTodos);
		rbTodos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tNControl.setEnabled(true);
				tNombre.setEnabled(true);
				tPApellido.setEnabled(true);
				tSApellido.setEnabled(true);
				cSemestre.setEnabled(true);
				cCarrera.setEnabled(true);
			}
		});
		
	
		rbNControl.setBounds(150,100, 20,20);
		bGrupo.add(rbNControl);
		add(rbNControl);
		rbNControl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tNControl.setEnabled(true);
				tNombre.setEnabled(false);
				tPApellido.setEnabled(false);
				tSApellido.setEnabled(false);
				cSemestre.setEnabled(false);
				cCarrera.setEnabled(false);
				blanquear();
			}
		});
		
		
		lNControl.setBounds(180,100, 180, 20);
		add(lNControl);
		tNControl.setBounds(330,100, 200, 20);
		add(tNControl);
		
		rbNombre.setBounds(150,130, 20,20);
		bGrupo.add(rbNombre);
		add(rbNombre);
		rbNombre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tNControl.setEnabled(false);
				tNombre.setEnabled(true);
				tPApellido.setEnabled(false);
				tSApellido.setEnabled(false);
				cSemestre.setEnabled(false);
				cCarrera.setEnabled(false);
				blanquear();
			}
		});
		
		lNombre.setBounds(180,130,80, 20);
		add(lNombre);
		tNombre.setBounds(250,130, 280, 20);
		add(tNombre);
		
		rbPApellido.setBounds(150,160, 20,20);
		bGrupo.add(rbPApellido);
		add(rbPApellido);
		rbPApellido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tNControl.setEnabled(false);
				tNombre.setEnabled(false);
				tPApellido.setEnabled(true);
				tSApellido.setEnabled(false);
				cSemestre.setEnabled(false);
				cCarrera.setEnabled(false);
				blanquear();
			}
		});
		
		
		lPApellido.setBounds(180,160, 180, 20);
		add(lPApellido);
		tPApellido.setBounds(320,160, 210, 20);
		add(tPApellido);
		

		rbSApellido.setBounds(150,190, 20,20);
		bGrupo.add(rbSApellido);
		add(rbSApellido);
		rbSApellido.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tNControl.setEnabled(false);
				tNombre.setEnabled(false);
				tPApellido.setEnabled(false);
				tSApellido.setEnabled(true);
				cSemestre.setEnabled(false);
				cCarrera.setEnabled(false);
				blanquear();
			}
		});
		
		lSApellido.setBounds(180,190, 180, 20);
		add(lSApellido);
		tSApellido.setBounds(320,190, 210, 20);
		add(tSApellido);
		
		

		rbSemestre.setBounds(150,220, 20,20);
		bGrupo.add(rbSemestre);
		add(rbSemestre);
		rbSemestre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tNControl.setEnabled(false);
				tNombre.setEnabled(false);
				tPApellido.setEnabled(false);
				tSApellido.setEnabled(false);
				cSemestre.setEnabled(true);
				cCarrera.setEnabled(false);
				blanquear();
			}
		});
		lSemestre.setBounds(180,220, 180, 20);
		add(lSemestre);
		cSemestre.setBounds(320,220, 210, 20);
		cSemestre.addItem("0");
		cSemestre.addItem("1");
		cSemestre.addItem("2");
		cSemestre.addItem("3");
		cSemestre.addItem("4");
		cSemestre.addItem("5");
		cSemestre.addItem("6");
		add(cSemestre);
		
		

		rbCarrera.setBounds(150,250, 20,20);
		bGrupo.add(rbCarrera);
		add(rbCarrera);
		rbCarrera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tNControl.setEnabled(false);
				tNombre.setEnabled(false);
				tPApellido.setEnabled(false);
				tSApellido.setEnabled(false);
				cSemestre.setEnabled(false);
				cCarrera.setEnabled(true);
				blanquear();
			}
		});
		
		
		
		lCarrera.setBounds(180,250, 180, 20);
		add(lCarrera);
		cCarrera.setBounds(320,250, 210, 20);
		cCarrera.addItem("0");
		cCarrera.addItem("ISC");
		cCarrera.addItem("IM");
		cCarrera.addItem("CP");
		cCarrera.addItem("IA");
		cCarrera.addItem("LA");
		add(cCarrera);
		
		bBuscar.setBounds(560,110 , 100, 35);
		bBuscar.addActionListener(this);
		bBuscar.setIcon(iconoBuscar);
		add(bBuscar);
		
		bBorrar.setBounds(560,170 , 100, 20);
		bBorrar.addActionListener(this);
		add(bBorrar);
		
		bCancelar.setBounds(560,220 , 100, 20);
		bCancelar.addActionListener(this);
		add(bCancelar);
		
		escroll.setBounds(90, 320, 500, 100);
		add(escroll);
		
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
			String sql = "SELECT * FROM Alumnos";
			if(rbNControl.isSelected()) {
				if(tNControl.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Ingresa un # de Control a Consultar");
				}else {
				sql = sql + (" WHERE NumControl = '"+tNControl.getText()+"'");
				atuaclizaTabla(sql);
				blanquear();
				}
			}else if(rbNombre.isSelected()) {
				if(tNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Ingresa un Nombre a Consultar");
				}else {
				sql = sql + (" WHERE Nombre ='"+tNombre.getText()+"'");
				atuaclizaTabla(sql);
				blanquear();
				}
			}else if(rbPApellido.isSelected()) {
				if(tPApellido.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Ingresa un Primer Apellido a Consultar");
				}else {
				sql = sql + (" WHERE PrimerAp ='"+tPApellido.getText()+"'");
				atuaclizaTabla(sql);
				blanquear();
				}
			}else if(rbSApellido.isSelected()) {
				if(tSApellido.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Ingresa un Segundo Apellido a Consultar");
				}else {
				sql = sql + (" WHERE SegundoAP ='"+tSApellido.getText()+"'");
				atuaclizaTabla(sql);
				blanquear();
				}
			}else if(rbSemestre.isSelected()) {
				if(cSemestre.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null,"Selecciona un Semestre valido a Consultar");
				}else {
				sql = sql + (" WHERE Semestre ='"+cSemestre.getSelectedItem()+"'");
				atuaclizaTabla(sql);
				blanquear();
				}
			}else if(rbCarrera.isSelected()) {
				if(cCarrera.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(null,"Selecciona una Carrera valido a Consultar");
				}else {
				sql = sql +  (" WHERE Carrera ='"+cCarrera.getSelectedItem()+"'");
				atuaclizaTabla(sql);
				blanquear();
				}
		}else if(rbTodos.isSelected()) {
			if(tNControl.getText().equals("")||tNombre.getText().equals("")||tPApellido.getText().equals("")||tSApellido.getText().equals("")||cSemestre.getSelectedIndex()==0||cCarrera.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(null,"Llene todos los filtros para proceder con la consulta");
			}else {
				sql = sql + (" WHERE NumControl = '"+tNControl.getText()+"'") + (" AND Nombre ='"+tNombre.getText()+"'") 
						+ (" AND PrimerAp ='"+tPApellido.getText()+"'") + (" AND SegundoAP ='"+tSApellido.getText()+"'")
						+(" AND Semestre ='"+cSemestre.getSelectedItem()+"'") + (" AND Carrera ='"+cCarrera.getSelectedItem()+"'");
					atuaclizaTabla(sql);
					blanquear();
			}
		}
		
		}else if(e.getSource()==bBorrar) {
			blanquear();
		}else if(e.getSource()==bCancelar) {
			setVisible(false);
		}
	}
}





class VentanaPrincipal extends JFrame{
	JMenuBar menuBar;
	JMenu controlAlumnos;
	JMenuItem altas;
	JMenuItem bajas;
	JMenuItem cambios;
	JMenuItem consultas;
	
	
	
	public VentanaPrincipal() {
		getContentPane().setLayout(null);
		setSize(700,500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Menu");
		setBackground(new Color(0, 0, 0));
		this.getContentPane().setBackground(new Color(192, 192, 192));
		
		setVisible(true);
		
		JLabel bienvenido = new JLabel("Bienvenido");
		
		
		bienvenido.setFont(new Font("Arial Black", Font.PLAIN, 40));
		bienvenido.setBounds(220,150,500,35);
		add(bienvenido);
		
		
		menuBar = new JMenuBar();
			controlAlumnos = new JMenu("Alumnos");
				altas = new JMenuItem("Altas");
				bajas = new JMenuItem("Bajas");
				cambios = new JMenuItem("Modificaciones");
				consultas = new JMenuItem("Consultas");
			controlAlumnos.add(altas);
			controlAlumnos.add(bajas);
			controlAlumnos.add(cambios);
			controlAlumnos.add(consultas);
		menuBar.add(controlAlumnos);
		setJMenuBar(menuBar);
		
		altas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new VentanaAltas();
					}
				});
			}
		});

		bajas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new VentanaBajas();
						
					}
				});
			}
		});
		
		cambios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new VentanaCambios();
					}
				});
			}
		});
	
		consultas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new VentanaConsultas();
					}
				});
			}
		});
		
		
		
		
		
	}
	
	
}//VENTANA




public class VentanaInicio {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaPrincipal();
			}
		});
		
		
		

	}

}
