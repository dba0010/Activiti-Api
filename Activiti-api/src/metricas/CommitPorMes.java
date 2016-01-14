package metricas;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Double;

public class CommitPorMes extends Metrica
{
	private Descripcion descripcion;
	
	private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	public CommitPorMes()
	{
		descripcion = new Descripcion("Restricciones temporales", "CommitPorMes", "Muestra el numero de commits realizados cada mes",
				"¿Cuantos commits se han realizado cada mes?", "C commits por mes", "C >= 0 mejor valores altos",
				"Absoluta", "C contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Conjunto valores = new Conjunto();
		Calendar fecha = Calendar.getInstance();
		
		for(String key : meses)
		{
			valores.setValor(key, new Double(0));
		}
		
		int i = 0;
		for(Object x : lista)
		{
			fecha.setTime(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			i = fecha.get(Calendar.MONTH);
			Double aux = new Double(valores.getValor(meses[i]).getValor());
			aux.setValor(aux.getValor() + 1);;
			valores.setValor( meses[i], aux);
		}
		
		return valores;
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