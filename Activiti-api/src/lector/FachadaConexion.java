package lector;

import java.io.BufferedReader;
import java.io.IOException;

public interface FachadaConexion
{	
	public void obtenerMetricas(String usuario,String repositorio) throws IOException;
	
	public Object[] getResultados();
		
	public String[] getNombresRepositorio(String usuario) throws IOException;
	
	public String getNombreRepositorio();

	public char[] generarArchivo();
	
	public void leerArchivo(BufferedReader archivo);
	
	public int getPeticionesRestantes();

	public String comparar(FachadaConexion conexion2);
	
	public FachadaMetricas getMetricas();
}