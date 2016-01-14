package metricas;

import java.io.IOException;
import org.eclipse.egit.github.core.Repository;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

public class NumeroWatchers extends Metrica
{
	public NumeroWatchers()
	{
		descripcion = new Descripcion("Proceso de orientacion", "NumeroWatchers", "Muestra el numero de usuarios subscritos al proyecto",
				"¿Cuantos usuarios subscritos tiene el proyecto?", "NW watchers", "NW >= 0 mejor valores altos",
				"Absoluta", "NW contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(Repository dato) throws IOException 
	{
		Entero entero = new Entero(dato.getWatchers());
		return entero;
	}
}