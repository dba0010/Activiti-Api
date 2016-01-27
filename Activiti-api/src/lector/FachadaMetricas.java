package lector;

import motorMetricas.ResultadoMetrica;

public interface FachadaMetricas
{
	public Object[] getResultados();

	public String generarArchivo();

	public String comparar(FachadaConexion comparacion);
	
	public ResultadoMetrica getResultadoMetrica();
}