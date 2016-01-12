package metricas;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Cadena;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Double;

public class ContadorCambiosPico extends Metrica
{
	private Descripcion descripcion;
	
	private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	public ContadorCambiosPico()
	{
		descripcion = new Descripcion("REstricciones temporales", "ContadorCambiosPico", "Muestra el número de cambios en el mes que mas se han realizado, normalizado sobre el número total de cambios.",
				"¿Cuál es la proporción de trabajo realizado en el mes con mayor número de cambios?", "CCP = NCMP (Número de cambios en el Mes Pico) / NTC (Número total de cambios)", "0 <= CCP <= 1 Mejor valores intermedios.",
				"Ratio", "NCMP contador, NTC contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Conjunto commits = new Conjunto();
		Calendar fecha = Calendar.getInstance();
		
		for(String key : meses)
		{
			commits.setValor(key, new Double(0));
		}
		
		int i = 0;
		for(Object x : lista)
		{
			fecha.setTime(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			i = fecha.get(Calendar.MONTH);
			Double aux = new Double(commits.getValor(meses[i]).getValor());
			aux.setValor(aux.getValor() + 1);;
			commits.setValor( meses[i], aux);
		}
		
		Double max = new Double(0);
		Cadena mes = new Cadena();
		
		for(String key : commits.getValor().keySet())
		{
			if(max.getValor() == 0 || max.getValor() < commits.getValor(key).getValor())
			{
				mes.setValor(key);
				max.setValor(commits.getValor(key).getValor());
			}
		}
		
		return new Double((double)max.getValor()/lista.size());
	}
	
	public Valor run(List<?> lista, List<?> lista2) throws IOException 
	{
		return null;
	}
	
	public Valor run(Repository dato) throws IOException 
	{
		return null;
	}
	
	public Descripcion getDescripcion()
	{
		return descripcion;
	}

	public void check() 
	{		
	}
}