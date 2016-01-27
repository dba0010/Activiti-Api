package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;

public class CommitPorMes extends Metrica
{
	
	
	public CommitPorMes()
	{
		descripcion = new Descripcion("Restricciones temporales", "CommitPorMes", "Muestra el número de commits realizados cada mes",
				"¿Cuántos commits se han realizado cada mes?", "C commits por mes", "C >= 0 mejor valores altos",
				"Absoluta", "C contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		return obtenerCambiosXMesGitHub(lista);
	}
}