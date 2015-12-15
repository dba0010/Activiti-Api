package lector;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.RepositoryCommit;

public class MetricasGitHub implements FachadaMetricas
{	
	private int numCambios = 0;
	private int numIssues = 0;
	private int numIssuesCerradas = 0;
	private double mediaDiasCambios = 0;
	private double mediaDiasCierre = 0;
	private double mediaDiasLinea = 0;
	private Date ultimaModificacion = null;
	private double porcentajeIssuesCerradas = 0;
	
	private DecimalFormat formateador = new DecimalFormat("###0.00"); 
	
	public MetricasGitHub(List<Issue> issues, List<RepositoryCommit> commits)
	{
		this.numCambios = 0;
		this.numIssues = 0;
		this.numIssuesCerradas = 0;
		this.mediaDiasCambios = 0;
		this.mediaDiasCierre = 0;
		this.mediaDiasLinea = 0;
		this.ultimaModificacion = null;
		this.porcentajeIssuesCerradas = 0;
		this.calcularNumIssues(issues);
		this.calcularNumCambiosSinMensaje(commits);
		this.calcularNumIssues(issues);
		this.calcularNumIssuesCerradas(issues);
		this.calcularMediaDiasCierre(issues);
		this.calcularMediaDiasEntreCambios(commits);
		this.calcularMediaDiasPorLinea(commits);
		this.calcularUltimaModificacion(issues, commits);
		this.calcularPorcentajeIssuesCerradas(issues);
	}
	
	public void calcularNumCambiosSinMensaje(List<RepositoryCommit> commits) 
	{
		int aux = 0;
		
		for(RepositoryCommit x : commits)
		{
			if(x.getCommit().getMessage() == "")
			{
				aux++;
			}
		}
		this.numCambios = aux;
	}

	public void calcularNumIssues(List<Issue> issues) 
	{
		this.numIssues = issues.size();
	}
	
	public void calcularNumIssuesCerradas(List<Issue> issues) 
	{
		int cerradas = 0;
		
		for(Issue x : issues)
		{
			if(x.getState().equals("close"))
			{
				cerradas++;
			}
		}
		
		this.numIssuesCerradas = cerradas;
	}
	
	public void calcularMediaDiasCierre(List<Issue> issues) 
	{
		double mediaDias = 0;
		int cerradas = 0;
		
		for(Issue x : issues)
		{
			if(x.getState().equals("close"))
			{
				cerradas++;
				mediaDias += (x.getClosedAt().getTime() - x.getCreatedAt().getTime() )/ (1000 * 60 * 60 * 24);
			}
		}
		
		mediaDias /= cerradas;
		
		
		this.mediaDiasCierre = mediaDias;
	}

	public void calcularMediaDiasEntreCambios(List<RepositoryCommit> commits) 
	{
		double mediaDias = 0;
		
		for(int i = 0; i < commits.size()-1; i++)
		{
			mediaDias += commits.get(i).getCommit().getAuthor().getDate().getTime() - commits.get(i+1).getCommit().getAuthor().getDate().getTime();
		}
		
		mediaDias /= (1000 * 60 * 60 * 24);
		mediaDias /= commits.size();
		
		this.mediaDiasCambios = mediaDias;		
	}

	public void calcularMediaDiasPorLinea(List<RepositoryCommit> commits) 
	{
		double dias = 0;
		int lineas = 0;
		
		for(int i = 0; i < commits.size(); i++)
		{
			lineas += commits.get(i).getStats().getTotal();
			if(i < commits.size() - 1)
			{
				dias += commits.get(i).getCommit().getAuthor().getDate().getTime() - commits.get(i+1).getCommit().getAuthor().getDate().getTime();
			}			
		}
			
		this.mediaDiasLinea = dias / (1000 * 60 * 60 * 24) / lineas;
	}
	
	public void calcularPorcentajeIssuesCerradas(List<Issue> issues)
	{		
		this.porcentajeIssuesCerradas = this.numIssuesCerradas * 100 / this.numIssues;
	}
	
	public void calcularUltimaModificacion(List<Issue> issues, List<RepositoryCommit> commits)
	{
		Date ultimaModificacion = null;
		
		for(Issue x : issues)
		{
			if(ultimaModificacion == null || x.getUpdatedAt().after(ultimaModificacion))
			{
				ultimaModificacion =  x.getUpdatedAt();
			}
		}
		for(RepositoryCommit y : commits)
		{
			if(ultimaModificacion == null || y.getCommit().getAuthor().getDate().after(ultimaModificacion))
			{
				ultimaModificacion =  y.getCommit().getAuthor().getDate();
			}
		}
		
		this.ultimaModificacion = ultimaModificacion;
	}

	public int getNumCambios() 
	{
		return numCambios;
	}
	
	public int getNumIssues() 
	{
		return numIssues;
	}
	
	public int getNumIssuesCerradas() 
	{
		return numIssuesCerradas;
	}
	
	public double getMediaDiasCambios() 
	{
		return mediaDiasCambios;
	}

	public double getMediaDiasCierre() 
	{
		return mediaDiasCierre;
	}

	public double getMediaDiasLinea() 
	{
		return mediaDiasLinea;
	}

	public double getPorcentajeIssuesCerradas() 
	{
		return porcentajeIssuesCerradas;
	}
	
	public Date getUltimaModificacion() 
	{
		return ultimaModificacion;
	}

	public String toString()
	{
		return "Estadisticas:" + 
				"\n Número de issues: " + this.numIssues + 
				"\n Número de issues cerradas: " + this.numIssuesCerradas + 
				"\n Media de dias para cerrar issue: " + this.mediaDiasCierre + 
				"\n Porcentaje de issues cerradas: " + this.porcentajeIssuesCerradas + 
				"\n Numero de commits sin mensaje: " + this.numCambios + 
				"\n Media de dias por commit: " + formateador.format(this.mediaDiasCambios) + 
				"\n Media de dias por linea: " + formateador.format(this.mediaDiasLinea) +
				"\n Ultima modificacion: " + this.ultimaModificacion;
	}
}