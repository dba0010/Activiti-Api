package metricas;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Double;

public class CommitPorDia extends Metrica
{
	private Descripcion descripcion;
	
	private String[] dias = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
	
	public CommitPorDia()
	{
		descripcion = new Descripcion("Estadistica", "Numero de commits por dia de la semana", "Muestra el numero de commits realizados cada dia de la semana",
				"¿Cuantos commits se han realizado cada dia de la semana?", "C commits por dia", "C >= 0 mejor valores altos",
				"Absoluta", "C contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Conjunto valores = new Conjunto();
		Calendar fecha = Calendar.getInstance();
		
		for(String key : dias)
		{
			valores.setValor(key, new Double(0));
		}
		
		int i = 0;
		for(Object x : lista)
		{
			fecha.setTime(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			i = fecha.get(Calendar.DAY_OF_WEEK) - 1;
			Double aux = new Double(valores.getValor(dias[i]).getValor());
			aux.setValor(aux.getValor() + 1);;
			valores.setValor( dias[i], aux);
		}
		
		return valores;
	}
	
	public Descripcion getDescripcion()
	{
		return descripcion;
	}

	public void check() 
	{		
	}
}