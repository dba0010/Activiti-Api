package datos;


import java.io.Serializable;


public class RepositorioBitbucket implements Serializable, Repositorio
{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String name;
	private String full_name;
	private boolean has_issues;
	private String created_on;
	private String updated_on;
	
	
	
	public String getUuid() 
	{
		return uuid;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getFullName()
	{
		return full_name;
	}
		
	public boolean getHasIssues() 
	{
		return has_issues;
	}
	
	public String getCreatedAt() 
	{
		return created_on;
	}
	
	public String getUpdatedAt() 
	{
		return updated_on;
	}
	
	public String toString() 
	{
		return "Repositorio \n id: " + this.uuid + 
				"\n fullname: " + this.full_name +
				"\n name: " + this.name +
				"\n has issues: " + this.has_issues +
				"\n Created at: " + this.created_on +
				"\n Updated at: " + this.updated_on;
	}
}
