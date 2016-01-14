package metricas;

import java.io.IOException;
import java.util.List;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Cadena;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Double;

public class RelacionMesPico extends Metrica
{
	public RelacionMesPico()
	{
		descripcion = new Descripcion("Restricciones temporales", "RelacionMesPico", "Muestra el mes en que más cambios se han realizado.",
				"¿Cuál es el mes en que más cambios se han realizado?", "M mes en el que mas cambios se han realizado", "",
				"Nominal", "M mes", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Conjunto commits = obtenerCambiosXMesGitHub(lista);
		
		Double max = new Double(0);
		Cadena valor = new Cadena();
		
		for(String key : commits.getValor().keySet())
		{
			if(max.getValor() == 0 || max.getValor() < commits.getValor(key).getValor())
			{
				valor.setValor(key);
				max.setValor(commits.getValor(key).getValor());
			}
		}
		
		return valor;
	}
}