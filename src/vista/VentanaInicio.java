package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import controlador.AlumnoDAO;
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
	JTable tabla = new JTable(6,6);
	
	public VentanaAltas() {
		getContentPane().setLayout(null);
		setSize(700,500);
		setLocationRelativeTo(null);
		setTitle("Contron Escolar");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
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
		
		tabla.setBounds(90, 320, 500, 100);
		add(tabla);
		
		
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
			//El 21 es la edad, un dato estatico(mi edad) que sera ignorado
			Alumno a = new Alumno(nControl,nombre,pApellido,sApellido,(byte)21,bsemestre,carrera);
			AlumnoDAO aDAO = new AlumnoDAO();
			aDAO.insertarRegistro(a);
			
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
	JTable tabla = new JTable(6,6);
	
	public VentanaBajas() {
		getContentPane().setLayout(null);
		setSize(700,500);
		setLocationRelativeTo(null);
		setTitle("Contron Escolar");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
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
		
		tabla.setBounds(90, 350, 500, 100);
		add(tabla);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bBuscar) {
				
			
			
			
			
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
			String x = tNControl.getText();
			AlumnoDAO aDAO = new AlumnoDAO();
			aDAO.eliminarRegistro("x");
			
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
		
		setVisible(true);
		
		menuBar = new JMenuBar();
			controlAlumnos = new JMenu("Control de Alumnos");
				altas = new JMenuItem("Altas Alumnos");
				bajas = new JMenuItem("Bajas Alumnos");
				cambios = new JMenuItem("Modificaciones Alumnos");
				consultas = new JMenuItem("Consultas Alumnos");
			controlAlumnos.add(altas);
			controlAlumnos.add(bajas);
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
