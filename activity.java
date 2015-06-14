// Simulate API from Aggie Feed v1.0
// Shawn Xiang

public class activity {
	private String title; 
	private String ucdEdusModelURL; 
	private String ucdEdusModelDisplayName; 
	private String ucdEdusModelLocation; 
	private String published; // time
	private String content; 
	
	public activity() {
		
	}
	
	public activity(String titles, String url, String name, 
			String location, String time, String message) {
		this.title = titles; 
		this.ucdEdusModelURL = url;
		this.ucdEdusModelDisplayName = name; 
		this.ucdEdusModelLocation = location; 
		this.published = time; 
		this.content = message; 
		
	}
	
	public void setTitles(String titles){
		this.title = titles; 
	}
	
	public void setURL (String url){
		this.ucdEdusModelURL = url;
	}
	
	public void setName (String name){
		this.ucdEdusModelDisplayName = name;
	}
	
	public void setTime(String time){
		this.published = time;
	}
	
	public void setContent(String message){
		this.content = message; 
	}
	
	public String getTitles(){
		return this.title;
	}
	
	public String getURL (){
		return this.ucdEdusModelURL; 
	}
	
	public String getName (){
		return this.ucdEdusModelDisplayName; 
	}
	
	public String getTime () {
		return this.published; 
	}
	
	public String getContent (){
		return this.content; 
	}
	
	
	@Override
	public String toString() {
		return new StringBuffer("title : ").append(this.title)
				.append("Url : ").append(this.ucdEdusModelURL)
				.append("Location : ").append(this.ucdEdusModelLocation).append("Posted by : ")
				.append(this.ucdEdusModelDisplayName).append("Time: "). append (this.published) 
				.append("Content: \n").append(this.content).toString();
	}
}
