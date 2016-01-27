package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Largo;

public class ContadorCambiosPico extends Metrica
{
	public ContadorCambiosPico()
	{
		descripcion = new Descripcion("Restricciones temporales", "ContadorCambiosPico", "Muestra el número de cambios en el mes que mas se han realizado, normalizado sobre el número total de cambios.",
				"¿Cuál es la proporción de trabajo realizado en el mes con mayor número de cambios?", "CCP = NCMP (Número de cambios en el Mes Pico) / NTC (Número total de cambios)", "0 <= CCP <= 1 Mejor valores intermedios.",
				"Ratio", "NCMP contador, NTC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Conjunto commits = obtenerCambiosXMesGitHub(lista);
				
		Largo max = new Largo(0);
		
		for(String key : commits.getValor().keySet())
		{
			if(max.getValor() == 0 || max.getValor() < commits.getValor(key).getValor())
			{
				max.setValor(commits.getValor(key).getValor());
			}
		}
		
		if(lista.size() == 0)
		{
			return new Largo(0);
		}
		else
		{
			return new Largo((double)max.getValor()/lista.size());
		}
	}
}