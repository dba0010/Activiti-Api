package datos;

public interface Repositorio
{
	String name = "";
	
	String full_name = "";
	
	boolean has_issues = true;
	
	public String getName();
	
	public String getFullName();
	
	public boolean getHasIssues();
	
	public String toString();
}