package datos;

import java.io.Serializable;

public class CommitBitbucket implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	String hash;
	Links links;
	String date;
	String message;
	
	public String getHash() 
	{
		return hash;
	}

	public Links getLinks() 
	{
		return links;
	}

	public String getDate() 
	{
		return date;
	}

	public String getMessage() 
	{
		return message;
	}

	public String toString()
	{
		return "Commit: " +
				"\nhash: " + hash +
				"\nlinks: " + links.toString() +
				"\ndate: " + date.toString() +
				"\nmessage: " + message;
	}

	class Links
	{
		Link self;
		Link comment;
		Link patch;
		Link html;
		Link diff;
		Link aprove;
		
		public String toString()
		{
			String resultado = "";
			if( self != null) resultado += "\n\t self: " + self.toString();
			if( comment != null)resultado += "\n\t comment: " + comment.toString();
			if( patch != null)resultado += "\n\t patch: " + patch.toString();
			if( html != null)resultado += "\n\t html: " + html.toString();
			if( diff != null)resultado += "\n\t diff: " + diff.toString();
			if( aprove != null)resultado += "\n\t aprove: " + aprove.toString();
			
			return resultado;
		}
		
		class Link
		{
			String href;
			
			public String toString()
			{
				return href;
			}
		}
	}
}