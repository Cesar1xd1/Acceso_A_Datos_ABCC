package vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import controlador.AlumnoDAO;
import modelo.Alumno;

class Ventana extends JFrame implements ActionListener{
	
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
	
	
	public Ventana() {
		getContentPane().setLayout(null);
		setSize(700,500);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Contron Escolar");
		
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
	
	
}




public class VentanaInicio {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Ventana();
			}
		});
		
		
		

	}

}
