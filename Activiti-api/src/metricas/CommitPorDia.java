package metricas;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.eclipse.egit.github.core.RepositoryCommit;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Conjunto;
import motorMetricas.valores.Entero;

public class CommitPorDia extends Metrica
{
	private String[] dias = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
	
	public CommitPorDia()
	{
		descripcion = new Descripcion("Restricciones temporales", "CommitPorDia", "Muestra el número de commits realizados cada día de la semana",
				"¿Cuántos commits se han realizado cada día de la semana?", "CPD commits por día", "CPD >= 0 mejor valores altos",
				"Absoluta", "CPD contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(List<?> lista) throws IOException
	{
		Conjunto valores = new Conjunto();
		Calendar fecha = Calendar.getInstance();
		
		for(String key : dias)
		{
			valores.setValor(key, new Entero(0));
		}
		
		int i = 0;
		for(Object x : lista)
		{
			fecha.setTime(((RepositoryCommit) x).getCommit().getAuthor().getDate());
			i = fecha.get(Calendar.DAY_OF_WEEK) - 1;
			valores.setValor( dias[i], new Entero(valores.getValor(dias[i]).getValor() + 1));
		}
		
		return valores;
	}
}