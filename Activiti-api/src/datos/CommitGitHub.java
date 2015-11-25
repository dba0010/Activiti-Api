package datos;

import java.io.Serializable;
import java.util.Date;


public class CommitGitHub implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Commit commit;
	private Stats stats;
	private String sha;
	private String html_url;
	
	public Commit getCommit() 
	{
		return commit;
	}
	public String getSha() 
	{
		return sha;
	}
	public String getHtml_url() 
	{
		return html_url;
	}
	public Stats getStats() 
	{
		return stats;
	}
	
	public String toString()
	{
		return "Full commit:" + 
				"\nsha: " + this.sha + 
				"\nhtml_url: " + this.html_url + 
				"\ncommit: " + this.commit + 
				"\nstats: " + this.stats;
	}
    
    public class Commit
    {
    	private String url;
    	private String message;
    	private Author author;
    	private Author committer;
    	private int comment_count;
    	
		public String getUrl() 
		{
			return url;
		}
		
		public String getMessage() 
		{
			return message;
		}
		
		public Author getAuthor() 
		{
			return author;
		}
		
		public Author getCommitter() 
		{
			return committer;
		}
		
		public int getComment_count() 
		{
			return comment_count;
		}
		
		public String toString()
		{
			return "\nurl: " + this.url + 
					"\nmessage: " + this.message +
					"\nauthor: " + this.author +
					"\ncommitter: " + this.committer +
					"\ncomment_count: " + this.comment_count;
		}
		
		public class Author
		{
			
			private String name;
			
			private String email;
			
			private Date date;

			public String getName() 
			{
				return name;
			}

			public String getEmail() 
			{
				return email;
			}

			public Date getDate() 
			{
				return date;
			}
			
			public String toString()
			{
				return "\nname: " + this.name + 
						"\nemail: " + this.email +
						"\ndate: " + this.date;
			}
		}		
    }
    
    public class Stats
    {
    	private int total;
    	private int additions;
    	private int deletions;
    	
		public int getTotal() 
		{
			return total;
		}
		public int getAdditions() 
		{
			return additions;
		}
		public int getDeletions() 
		{
			return deletions;
		}
    	
		public String toString()
		{
			return "\ntotal: " + this.total + 
					"\nadditions: " + this.additions +
					"\ndeletions: " + this.deletions;
		}
    }

}

