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
	private String full_name;
	private int open_issues_count;
	private boolean has_issues;
	private String pushed_at;
	private String created_at;
	private String updated_at;
	
	
	
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
		return full_name;
	}
	
	public int getNumIssues() 
	{
		return open_issues_count;
	}
	
	public boolean getHasIssues() 
	{
		return has_issues;
	}
	
	public String getPushedAt() 
	{
		return pushed_at;
	}
	
	public String getCreatedAt() 
	{
		return created_at;
	}
	
	public String getUpdatedAt() 
	{
		return updated_at;
	}
	
	public String toString() 
	{
		return "Repositorio \n id: " + this.id + 
				"\n Title: " + this.full_name +
				"\n State: " + this.name +
				"\n Description: " + this.open_issues_count +
				"\n Comments: " + this.has_issues +
				"\n Closed at: " + this.pushed_at +
				"\n Created at: " + this.created_at +
				"\n Updated at: " + this.updated_at;
	}
}
