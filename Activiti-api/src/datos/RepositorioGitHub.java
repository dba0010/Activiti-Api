package datos;


import java.io.Serializable;


public class RepositorioGitHub implements Serializable, Repositorio
{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String fullName;
	private int openIssuesCount;
	private boolean hasIssues;
	private String pushedAt;
	private String createdAt;
	private String updatedAt;
	
	
	
	public int getId() 
	{
		return id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getFullName()
	{
		return fullName;
	}
	
	public int getNumIssues() 
	{
		return openIssuesCount;
	}
	
	public boolean getHasIssues() 
	{
		return hasIssues;
	}
	
	public String getPushedAt() 
	{
		return pushedAt;
	}
	
	public String getCreatedAt() 
	{
		return createdAt;
	}
	
	public String getUpdatedAt() 
	{
		return updatedAt;
	}
	
	public String toString() 
	{
		return "Repositorio \n id: " + this.id + 
				"\n Title: " + this.fullName +
				"\n State: " + this.name +
				"\n Description: " + this.openIssuesCount +
				"\n Comments: " + this.hasIssues +
				"\n Closed at: " + this.pushedAt +
				"\n Created at: " + this.createdAt +
				"\n Updated at: " + this.updatedAt;
	}
}
