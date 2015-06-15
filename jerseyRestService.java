package com.javacodegeeks.enterprise.rest.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.util.Date;
import java.util.List;

import java.lang.Object; 
import java.lang.Number; 
import java.lang.Long; 

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Place; 
import com.restfb.types.Url;
import com.restfb.types.User;
import com.restfb.types.FacebookType; 
import com.restfb.types.Post.Likes;
import com.restfb.json.*;
// Example source : http://examples.javacodegeeks.com/enterprise-java/rest/jersey/json-example-with-jersey-jackson/


@Path("/jsonServices")
public class JerseyRestService {

	// URL to obtain an access token: https://developers.facebook.com/tools/explorer
	
	 static String ACCESS_TOKEN = "CAACEdEose0cBAAAn8v2FomnTHV3mi69Vv2dntwq3ZCP88zc9wPmLymZAGvZBDD5ddplD1tDOFHEpljztZAoPvPFI98HmQl85Ja6Uaw4nRUiTDdgMUhXMKiYI0lgmPwg4KNcFE30ooAk0yYAufka5B4LdqZBiOZAers3KMY1gSEbi1wOKqYEK1Lc5Fjn4ixtuJOmHcOc4Kc9D99AEehoZBfpZBoB8LOuzBuQZD";
	int sizeOfArray = 0; 
	
	@GET
	@Path("/print/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public activity[] produceJSON(@PathParam("name") String name ) {
		
		FacebookClient fbclient = new DefaultFacebookClient(ACCESS_TOKEN, Version.VERSION_2_3); 

		// Use /feed for all the posts, including fan posts. Use "to" and "until" to specify a date
		Connection<Post> message = fbclient.fetchConnection(name + "/posts", com.restfb.types.Post.class, Parameter.with("limit", 100)); 
		
		
		List<Post> detailedPost = message.getData(); 
		
		int size = detailedPost.size(); 
		
		sizeOfArray = size; 
		
		Date[] publishedDate = new Date[size]; 
		String[] numberOfLikes = new String[size]; 
		String[] pictureLink = new String[size]; 
		String[] linkCaption = new String[size]; 
		String[] linkDescription = new String[size]; 
		String[] externalLink = new String[size]; 
		String[] sourceLink = new String[size]; 
		String[] typeOfSourceLink = new String[size]; 
		Date [] lastCommented = new Date[size]; 
		String [] locationPosted = new String[size]; 
		String[] storyText = new String[size]; 
		String[] commentCount = new String[size]; 
		String[] personPosted = new String[size]; 
		
		//http://restfb.com/javadoc/com/restfb/types/Post.html
		
		for (int i = 0; i < detailedPost.size(); i++) {
			String id = message.getData().get(i).getId();
			personPosted[i] = detailedPost.get(i).getFrom().getName().toString(); 
		//	if (personPosted[i].equals(name)) {
			/*
		JsonObject jsonObject = fbclient.fetchObject(id + "/comments", JsonObject.class, 
	                Parameter.with("summary", true), Parameter.with("limit", 1));
		System.out.println(jsonObject); 
	    long commentsTotalCount = jsonObject.getJsonObject("summary").getLong("total_count");
	     commentCount[i] = Long.toString(commentsTotalCount); 
		
	     jsonObject = fbclient.fetchObject(id + "/likes", JsonObject.class, 
	                Parameter.with("summary", true), Parameter.with("limit", 1));
	    long likesTotalCount = jsonObject.getJsonObject("summary").getLong("total_count");
	     numberOfLikes[i] = Long.toString(likesTotalCount); 
	    */
	     Post postObject = fbclient.fetchObject(id, 
	    		  Post.class, 
	    		  Parameter.with("fields", "from,to,likes.summary(true),comments.summary(true)"));
	     numberOfLikes[i] = postObject.getLikes().getTotalCount().toString(); 
	    
	     commentCount[i] = postObject.getComments().getTotalCount().toString(); 
	     
	     
	     publishedDate[i] = detailedPost.get(i).getCreatedTime(); 
		 
		 linkCaption[i] = detailedPost.get(i).getCaption(); 
		 pictureLink[i] = detailedPost.get(i).getPicture();
		 linkDescription[i] = detailedPost.get(i).getDescription(); 
		 typeOfSourceLink[i] = detailedPost.get(i).getStatusType(); 
		 sourceLink[i] = detailedPost.get(i).getSource(); 
		 externalLink[i] = detailedPost.get(i).getLink(); 
		 lastCommented[i] = detailedPost.get(i).getUpdatedTime(); 
		 storyText[i] = detailedPost.get(i).getMessage(); 
		 if (detailedPost.get(i).getPlace() != null) {
		 locationPosted[i] = detailedPost.get(i).getPlace().getLocationAsString();
		    }
		 //} // if name matches
		}
		
		for (int i = 0; i < detailedPost.size(); i++) {
			
			//if (personPosted[i].equals(name)) {
			System.out.println("# "+ i ); 
			System.out.println("Likes: " + numberOfLikes[i]); 
			System.out.println("Comments: "+ commentCount[i]); 
			if (storyText[i] != null)
			System.out.println(storyText[i]); 
				
			if (sourceLink[i] != null)
				System.out.println("Link: " + sourceLink[i]); 
			if (externalLink[i] != null)
				System.out.println("External Link: " + externalLink[i]); 
			if (linkCaption[i] != null)
				System.out.println("Link Caption: " + linkCaption[i]); 
			if (linkDescription[i] != null)
				System.out.println("Link Description: " + linkDescription[i]); 
			if (typeOfSourceLink[i] != null)
				System.out.println("Type of link: " + typeOfSourceLink[i]); 
			if (pictureLink[i] != null)
				System.out.println("Picture link: " + pictureLink[i]); 
			
			System.out.println("Published Date: " + publishedDate[i]); 
			System.out.println("Last Commented: " + lastCommented[i]); 
			//} // if name matches
			}
		
		
		activity [] st = new activity[size+1]; 
		for (int k = 0; k < size; k++) {
			//if (personPosted[k].equals(name)) {
		st[k] = new activity(name, pictureLink[k], "Shawn", locationPosted[k], publishedDate[k].toString(), storyText[k]);
			//}  //if 
		}// for
		return st;

	} // get
	
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON( activity[] student ) {
		String output = "0"; 
		for (int i = 0; i < sizeOfArray; i++ )
		output = output + student[i].toString();

		return Response.status(200).entity(output).build();
	} //post


}
