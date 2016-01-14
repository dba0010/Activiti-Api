package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Double;

public class MediaDiasCierre extends Metrica
{
	public MediaDiasCierre()
	{
		descripcion = new Descripcion("Proceso de orientacion", "MediaDiasCierre", "Muestra los dias que se tarda en cerrar una issue, normalizado por el numero de issues cerradas",
				"¿Cuanto se tarda de media en cerrar una issue?", "MDC = D (suma de los dias) / NIC (numero de issues cerradas)", "MDC >= 0 mejor valores bajos",
				"Ratio", "D contador, NIC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{		
		double mediaDias = 0;
		int cerradas = obternerIssuesCerradasGitHub(lista).getValor();
		if(cerradas == 0){cerradas = 1;}
		mediaDias /= cerradas;
		
		return new Double(mediaDias);
	}
}