package controlador;
import conexionBD.ConexionBD;
import modelo.Alumno;

//DAD - Data Access Objetc


public class AlumnoDAO {

	ConexionBD conexion;
	
	public AlumnoDAO() {
		conexion = new ConexionBD();
	}
	
	public boolean insertarRegistro(Alumno a) {
		boolean resultado = false;
		
		//INSERT INTO Alumnos VALUES('1','1','1','1',1,1,'1');
		
		String sql = "INSERT INTO Alumnos VALUES('"+a.getNumControl()+"','"+a.getNombre()+"','"+a.getPrimerAp()+"','"+a.getSegundoAp()+"','"+a.getEdad()+"','"+a.getSemestre()+"','"+a.getCarrera()+"');";
		resultado = conexion.ejecutarInstruccion(sql);
		
		return resultado;
	}
	
	
}
