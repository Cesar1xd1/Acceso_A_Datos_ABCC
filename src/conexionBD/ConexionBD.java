package conexionBD;

import java.sql.*;

public class ConexionBD {
	private Connection conexion;
	private Statement stn;
	private ResultSet rs;
	
	public ConexionBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			String URL = "jdbc:mysql://localhost:3306/Escuela_Topicos";
			
			//La contraseña del profe
			conexion = DriverManager.getConnection(URL,"root","itsj");
		
			System.out.println("Conexion establecida!!");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error de DRIVER");
		} catch (SQLException throwables) {
			System.out.println("Error en la conexion de MySQL");
		}
	}

	public void cerrarConexion() {
		try {
			stn.close();
		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion");
			e.printStackTrace();
		}
	}
	
	//__ METODO PARA OPERACIONES DDL Y DML (ABC - ALTAS,BASJAS,CAMBIOS)
	public boolean ejecutarInstruccion(String sql) {
		try {
			stn = conexion.createStatement();
			int resultado = stn.executeUpdate(sql);
			return resultado==1?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("NO se pudo ejecutar la instruccion");
			return false;
		}
		
	}
		
	
	//__METODO PARA OPERACIONES DE CONSULTA
	public ResultSet ejecutarConsulta(String sql) {
		try {
			stn = conexion.createStatement();
			rs = stn.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Se pudo ejecutar la consulta");
			e.printStackTrace();
		}
		return rs;
	}
	
}
