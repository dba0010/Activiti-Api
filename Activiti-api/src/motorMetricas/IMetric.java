package motorMetricas;

import java.io.IOException;
import java.util.List;

public interface IMetric 
{
	public Valor calculate(List<?> lista, ResultadoMetrica metricResult) throws IOException;		
}