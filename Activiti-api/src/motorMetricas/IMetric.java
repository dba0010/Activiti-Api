package motorMetricas;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;

public interface IMetric 
{
	public Valor calculate(List<?> lista, ResultadoMetrica metricResult) throws IOException;	
	
	public Valor calculate(List<?> lista, List<?> lista2, ResultadoMetrica metricResult) throws IOException;	
	
	public Valor calculate(Repository dato, ResultadoMetrica metricResult) throws IOException;
}