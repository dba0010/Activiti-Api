package datos;


import java.io.Serializable;


public class IssueBitbucket implements Serializable 
{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String priority;	
	private String title;	
	private String utc_last_updated;	
	private int comment_count;	
	private Metadata metadata;	
	private String content;	
	private int local_id;	
	private int follower_count;	
	private String utc_created_on;	
	private String resource_uri;	
	private boolean is_spam;
	
	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getPriority() 
	{
		return priority;
	}

	public void setPriority(String priority) 
	{
		this.priority = priority;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getUtc_last_updated() 
	{
		return utc_last_updated;
	}

	public void setUtc_last_updated(String utc_last_updated) 
	{
		this.utc_last_updated = utc_last_updated;
	}

	public int getComment_count() 
	{
		return comment_count;
	}

	public void setComment_count(int comment_count) 
	{
		this.comment_count = comment_count;
	}

	public Metadata getMetadata() 
	{
		return metadata;
	}

	public void setMetadata(Metadata metadata) 
	{
		this.metadata = metadata;
	}

	public String getContent() 
	{
		return content;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}

	public int getLocal_id() 
	{
		return local_id;
	}

	public void setLocal_id(int local_id) 
	{
		this.local_id = local_id;
	}

	public int getFollower_count() 
	{
		return follower_count;
	}

	public void setFollower_count(int follower_count) 
	{
		this.follower_count = follower_count;
	}

	public String getUtc_created_on() 
	{
		return utc_created_on;
	}

	public void setUtc_created_on(String utc_created_on) 
	{
		this.utc_created_on = utc_created_on;
	}

	public String getResource_uri() 
	{
		return resource_uri;
	}

	public void setResource_uri(String resource_uri) 
	{
		this.resource_uri = resource_uri;
	}

	public boolean isIs_spam() 
	{
		return is_spam;
	}

	public void setIs_spam(boolean is_spam) 
	{
		this.is_spam = is_spam;
	}

	public String toString() 
	{
		return "issue \n Status: " + this.status +
				"\n Priority: " + this.priority +
				"\n Title: " + this.title +
				"\n Last update: " + this.utc_last_updated +
				"\n Comments: " + this.comment_count +
				"\n Content: " + this.content +
				"\n Id local: " + this.local_id +
				"\n Followers: " + this.follower_count +
				"\n Created at: " + this.utc_created_on +
				"\n Uri: " + this.resource_uri +
				"\n Spam: " + this.is_spam +
				"\n " + this.metadata.toString();
	}
	
	class Metadata
	{
		private String kind;
		private String version;
		private String component;
		private String milestone;
		
		public String getKind() 
		{
			return kind;
		}
		public void setKind(String kind) 
		{
			this.kind = kind;
		}
		public String getVersion() 
		{
			return version;
		}
		public void setVersion(String version) 
		{
			this.version = version;
		}
		public String getComponent() 
		{
			return component;
		}
		public void setComponent(String component) 
		{
			this.component = component;
		}
		public String getMilestone()
		{
			return milestone;
		}
		public void setMilestone(String milestone) 
		{
			this.milestone = milestone;
		}
		
		public String toString()
		{
			return "Metadata: \n\t kind: " + this.kind +
					"\n\t version: " + this.version +
					"\n\t compnent: " + this.component +
					"\n\t milestone: " + this.milestone;
		}
		
	}
}
