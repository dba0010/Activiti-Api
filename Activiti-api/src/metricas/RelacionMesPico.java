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

public class RelacionMesPico extends Metrica
{
	private Descripcion descripcion;
	
	private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	public RelacionMesPico()
	{
		descripcion = new Descripcion("Restricciones temporales", "RelacionMesPico", "Muestra el mes en que más cambios se han realizado.",
				"¿Cuál es el mes en que más cambios se han realizado?", "M mes en el que mas cambios se han realizado", "",
				"Nominal", "M mes", "Repositorio GitHub de un proyecto");
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