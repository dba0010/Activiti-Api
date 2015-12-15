package lector;

import java.util.List;

import org.eclipse.egit.github.core.*;

public interface FachadaMetricas
{
	public void calcularNumCambiosSinMensaje(List<RepositoryCommit> commits);
	
	public void calcularNumIssues(List<Issue> issues);
	
	public void calcularNumIssuesCerradas(List<Issue> issues);
	
	public void calcularMediaDiasCierre(List<Issue> issues);
	
	public void calcularMediaDiasEntreCambios(List<RepositoryCommit> commits);
	
	public void calcularMediaDiasPorLinea(List<RepositoryCommit> commits);
	
	public void calcularPorcentajeIssuesCerradas(List<Issue> issues);
	
	public void calcularUltimaModificacion(List<Issue> issues, List<RepositoryCommit> commits);
}