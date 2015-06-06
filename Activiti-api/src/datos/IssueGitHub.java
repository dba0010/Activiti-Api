package datos;


import java.io.Serializable;


public class IssueGitHub implements Serializable 
{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int number;
	private String state;
	private String title;
	private String description;
	private String comments;
	private String closed_at;
	private String created_at;
	private String updated_at;
	
	
	
	public int getId() 
	{
		return id;
	}
	
	public int getNumber() 
	{
		return number;
	}
	
	public String getState() 
	{
		return state;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public String getComments() 
	{
		return comments;
	}
	
	public String getClosedAt() 
	{
		return closed_at;
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
		return "issue \n id: " + this.id + 
				"\n Number: " + this.number + 
				"\n Title: " + this.title +
				"\n State: " + this.state +
				"\n Description: " + this.description +
				"\n Comments: " + this.comments +
				"\n Closed at: " + this.closed_at +
				"\n Created at: " + this.created_at +
				"\n Updated at: " + this.updated_at;
	}
}
