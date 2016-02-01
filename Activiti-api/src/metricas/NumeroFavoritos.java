package metricas;

import java.io.IOException;
import org.eclipse.egit.github.core.Repository;

import motorMetricas.Descripcion;
import motorMetricas.Metrica;
import motorMetricas.Valor;
import motorMetricas.valores.Entero;

public class NumeroFavoritos extends Metrica
{
	public NumeroFavoritos()
	{
		descripcion = new Descripcion("Proceso de orientación", "NumeroFavoritos", "Muestra el número de usuarios que han marcado al proyecto como favorito.",
				"¿Cuántos usuarios han declarado como favorito el proyecto?", "NF Favoritos", "NF >= 0 mejor valores altos",
				"Absoluta", "NF contador", "Repositorio GitHub de un proyecto");
	}
	
	public Valor run(Repository dato) throws IOException 
	{
		Entero entero = new Entero(dato.getWatchers());
		return entero;
	}
}