package lector;

import motorMetricas.ResultadoMetrica;

/**
 * Interace fachada metricas.
 * @author David Blanco Alonso
 */
public interface FachadaMetricas
{
	/**
	 * Realiza la comparacion con otra conexion.
	 * @param comparacion FachadaConexion conexion con la que comparar.
	 * @return String texto resultante del a comparacion.
	 */
	public String comparar(FachadaConexion comparacion);

	/**
	 * Genera un texto con los resultados obtenidos en las metricas.
	 * @return String texto del informe.
	 */
	public String generarArchivo();

	/**
	 * Devuelve los resutlados de l as metricas.
	 * @return ResultadoMetrica objeto con todas las medidas resultantes del cálculo de métricas.
	 */
	public ResultadoMetrica getResultadoMetrica();
	
	/**
	 * Metodo que devuelve los resultados de las metricas en formato texto y gráfico.
	 * @return Object[] resultados en formato texto y ChartPanel.
	 */
	public Object[] getResultados();
}