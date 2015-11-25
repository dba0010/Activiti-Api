package lector;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import datos.*;

public class MetricasGitHub<IssueGithub> implements FachadaMetricas
{
	private int num_Cambios;
	private int num_Issues;
	private int num_Issues_Cerradas;
	private double media_Dias_Cambios;
	private double media_Dias_Cierre;
	private double media_Dias_Linea;
	private Date ultima_Modificacion;
	private double porcentaje_Issues_Cerradas;
	
	private DecimalFormat formateador = new DecimalFormat("###0.00"); 
	
	public MetricasGitHub(ArrayList<IssueGitHub> issues, ArrayList<CommitGitHub> commits)
	{
		calcularNumIssues(issues);
		calcularNumCambiosSinMensaje(commits);
		calcularNumIssues(issues);
		calcularNumIssuesCerradas(issues);
		calcularMediaDiasCierre(issues);
		calcularMediaDiasEntreCambios(commits);
		calcularMediaDiasPorLinea(commits);
		calcularUltimaModificacion(issues, commits);
		calcularPorcentajeIssuesCerradas(issues);
	}

	public void calcularNumCambiosSinMensaje(ArrayList<?> commits) 
	{
		int aux = 0;
		
		for(Object x : commits)
		{
			if(((CommitGitHub) x).getCommit().getMessage() == "")
			{
				aux++;
			}
		}
		this.num_Cambios = aux;
	}

	public void calcularNumIssues(ArrayList<?> issues) 
	{
		this.num_Issues = issues.size();
	}
	
	public void calcularNumIssuesCerradas(ArrayList<?> issues) 
	{
		int cerradas = 0;
		
		for(Object x : issues)
		{
			if(((IssueGitHub) x).getState().equals("close"))
			{
				cerradas++;
			}
		}
		
		this.num_Issues_Cerradas = cerradas;
	}
	
	public void calcularMediaDiasCierre(ArrayList<?> issues) 
	{
		double mediaDias = 0;
		int cerradas = 0;
		
		for(Object x : issues)
		{
			if(((IssueGitHub) x).getState().equals("close"))
			{
				cerradas++;
				mediaDias += (((IssueGitHub) x).getClosedAt().getTime() - ((IssueGitHub) x).getCreatedAt().getTime())/ (1000 * 60 * 60 * 24);
			}
		}
		
		mediaDias /= cerradas;
		
		
		this.media_Dias_Cierre = mediaDias;
	}

	public void calcularMediaDiasEntreCambios(ArrayList<?> commits) 
	{
		double mediaDias = 0;
		
		for(int i = 0; i < commits.size()-1; i++)
		{
			mediaDias += ((CommitGitHub) commits.get(i)).getCommit().getAuthor().getDate().getTime() - ((CommitGitHub) commits.get(i+1)).getCommit().getAuthor().getDate().getTime();
		}
		
		mediaDias /= (1000 * 60 * 60 * 24);
		mediaDias /= commits.size();
		
		this.media_Dias_Cambios = mediaDias;		
	}

	public void calcularMediaDiasPorLinea(ArrayList<?> commits) 
	{
		double dias = 0;
		int lineas = 0;
		
		for(int i = 0; i < commits.size(); i++)
		{
			lineas += ((CommitGitHub) commits.get(i)).getStats().getTotal();
			if(i < commits.size() - 1)
			{
				dias += ((CommitGitHub) commits.get(i)).getCommit().getAuthor().getDate().getTime() - ((CommitGitHub) commits.get(i+1)).getCommit().getAuthor().getDate().getTime();
			}			
		}
			
		this.media_Dias_Linea = dias / (1000 * 60 * 60 * 24) / lineas;
	}
	
	public void calcularPorcentajeIssuesCerradas(ArrayList<?> issues)
	{		
		this.porcentaje_Issues_Cerradas = this.num_Issues_Cerradas * 100 / this.num_Issues;
	}
	
	public void calcularUltimaModificacion(ArrayList<?> issues, ArrayList<?> commits)
	{
		Date ultimaModificacion = null;
		
		for(Object x : issues)
		{
			if(ultimaModificacion == null || ((IssueGitHub) x).getUpdatedAt().after(ultimaModificacion))
			{
				ultimaModificacion = ((IssueGitHub) x).getUpdatedAt();
			}
		}
		for(Object y : commits)
		{
			if(ultimaModificacion == null || ((CommitGitHub) y).getCommit().getAuthor().getDate().after(ultimaModificacion))
			{
				ultimaModificacion = ((CommitGitHub) y).getCommit().getAuthor().getDate();
			}
		}
		
		this.ultima_Modificacion = ultimaModificacion;
	}

	public int getNum_Cambios() 
	{
		return num_Cambios;
	}
	
	public int getNum_Issues() 
	{
		return num_Issues;
	}
	
	public int getNum_Issues_Cerradas() 
	{
		return num_Issues_Cerradas;
	}
	
	public double getMedia_Dias_Cambios() 
	{
		return media_Dias_Cambios;
	}

	public double getMedia_Dias_Cierre() 
	{
		return media_Dias_Cierre;
	}

	public double getMedia_Dias_Linea() 
	{
		return media_Dias_Linea;
	}

	public double getPorcentaje_Issues_Cerradas() 
	{
		return porcentaje_Issues_Cerradas;
	}
	
	public Date getUltima_Modificacion() 
	{
		return ultima_Modificacion;
	}

	public String toString()
	{
		return "Estadisticas:" + 
				"\n Número de issues: " + this.num_Issues + 
				"\n Número de issues cerradas: " + this.num_Issues_Cerradas + 
				"\n Media de dias para cerrar issue: " + this.media_Dias_Cierre + 
				"\n Porcentaje de issues cerradas: " + this.porcentaje_Issues_Cerradas + 
				"\n Numero de commits sin mensaje: " + this.num_Cambios + 
				"\n Media de dias por commit: " + formateador.format(this.media_Dias_Cambios) + 
				"\n Media de dias por linea: " + formateador.format(this.media_Dias_Linea) +
				"\n Ultima modificacion: " + this.ultima_Modificacion;
	}
}