package lector;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface de las fachadas conexion.
 * @author David Blanco Alonso
 */
public interface FachadaConexion
{	
	/**
	 * Realiza la comparacion de esta conexion y sus resultados con los resultados de otra conexion.
	 * @param conexion2 FachadaConexion conexión con la que realizar la comparación.
	 * @return String resultado de la comparacion(contien filas de tabla HTML).
	 */
	public String comparar(FachadaConexion conexion2);
	
	/**
	 * Genera un informe con los resultados obtenidos en las metricas.
	 * @return Char[] texto del informe.
	 */
	public char[] generarArchivo();
		
	/**
	 * Devuelve la Fachada metricas que contiene los resultados de las metricas calculadas.
	 * @return FachadaMetricas metricas calculadas.
	 */
	public FachadaMetricas getMetricas();
	
	/**
	 * Devuelve el nombre del repositorio del que se ha realizado el calculo de las métricas.
	 * @return String nombre del repositorio.
	 */
	public String getNombreRepositorio();

	/**
	 * Metodo que devuelve los repositorios que pertenecen al usuario indicado.
	 * @param usuario String usuario del que obtner los repositorios. 
	 * @return String[] array que contien todos los respositorios de ese usuario.
	 * @throws IOException
	 */
	public String[] getNombresRepositorio(String usuario) throws IOException;
	
	/**
	 * Devulve las peticiones restantes para el cliente existente.
	 * @return int numero de peticiones.
	 */
	public int getPeticionesRestantes();
	
	/**
	 * Metodo que devuelve los resultados de las metricas en formato texto y gráfico.
	 * @return Object[] resultados en formato texto y ChartPanel.
	 */
	public Object[] getResultados();

	/**
	 * Realiza la lectura de un informe.
	 * @param archivo BufferedReader archivo del informe.
	 */
	public void leerArchivo(BufferedReader archivo);
	
	/**
	 * Metodo por el que se realiza el calculo de las metricas, despues de solicitar la información del repositorio indicado.
	 * @param usuario String usuario propietario del repositorio.
	 * @param repositorio String repositorio seleccionado.
	 * @throws IOException
	 */
	public void obtenerMetricas(String usuario,String repositorio) throws IOException;
}