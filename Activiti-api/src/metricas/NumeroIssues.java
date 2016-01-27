package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

public class NumeroIssues extends Metrica
{
	public NumeroIssues()
	{
		descripcion = new Descripcion("Proceso de orientación", "NumeroIssues", "Número de issues total creadas en el repositorio",
				"¿Cuántas issues se han creado en el repositorio?", "NI número de issues", "NI >= 0 mejor valores bajos",
				"Absoluta", "NI contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Entero entero = new Entero(lista.size());
		return entero;
	}
}