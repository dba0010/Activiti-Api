package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;

public class NumeroIssuesCerradas extends Metrica
{
	public NumeroIssuesCerradas()
	{
		descripcion = new Descripcion("Proceso de orientación", "NumeroIssuesCerradas", "Número de issues cerradas total en el repositorio",
				"¿Cuántas issues se han cerrado en el repositorio?", "NIC número de issues cerradas", "NIC >= 0 mejor valores altos",
				"Absoluta", "NIC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		return obternerIssuesCerradasGitHub(lista);
	}
}