package lector;

import java.util.ArrayList;

public interface FachadaMetricas
{
	public void calcularNumCambiosSinMensaje(ArrayList<?> commits);
	
	public void calcularNumIssues(ArrayList<?> issues);
	
	public void calcularNumIssuesCerradas(ArrayList<?> issues);
	
	public void calcularMediaDiasCierre(ArrayList<?> issues);
	
	public void calcularMediaDiasEntreCambios(ArrayList<?> commits);
	
	public void calcularMediaDiasPorLinea(ArrayList<?> commits);
	
	public void calcularPorcentajeIssuesCerradas(ArrayList<?> issues);
	
	public void calcularUltimaModificacion(ArrayList<?> issues, ArrayList<?> commits);
}