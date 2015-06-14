// Written by Shawn

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

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Place; 
import com.restfb.types.Url;
import com.restfb.types.User;
import com.restfb.types.FacebookType; 
// Example source : http://examples.javacodegeeks.com/enterprise-java/rest/jersey/json-example-with-jersey-jackson/


@Path("/jsonServices")
public class JerseyRestService {

	
	static String ACCESS_TOKEN = "CAACEdEose0cBAAobhgy9pSPs86ZB9HS2rh99qo7ElSQiolamAMUhcXCXnT1qPcQsdKoexR8ZBrGhh65VNiGjSXqerCF1iK52gq6nsyT1ZAOBaYujZCZB19EpdLDMBtWo6c93UrPqTf1hsK3jkkmNuZAzUzeUdwuB6FVxfCrZCnA2aViU1XOS50zuBn6NBNqHZAtl0M9Xo6QnjbeUHPl9BaxDeYhjlP0cChQZD";
	int sizeOfArray = 0; 
	
	@GET
	@Path("/print/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public activity[] produceJSON(@PathParam("name") String name ) {
		
		FacebookClient fbclient = new DefaultFacebookClient(ACCESS_TOKEN, Version.VERSION_2_3); 

		Connection<Post> message = fbclient.fetchConnection(name + "/feed", com.restfb.types.Post.class,  Parameter.with("until","3/1/2015" ), Parameter.with("to", "1/1/2014"), Parameter.with("limit", 100)); 
		
		List<Post> detailedPost = message.getData(); 
		
		int size = detailedPost.size(); 
		
		sizeOfArray = size; 
		
		Date[] publishedDate = new Date[size]; 
		//String[] numberOfLikes = new String[size]; 
		String[] pictureLink = new String[size]; 
		String[] linkCaption = new String[size]; 
		String[] linkDescription = new String[size]; 
		String[] externalLink = new String[size]; 
		String[] sourceLink = new String[size]; 
		String[] typeOfSourceLink = new String[size]; 
		Date [] lastCommented = new Date[size]; 
		String [] locationPosted = new String[size]; 
		String[] storyText = new String[size]; 
		
		
		for (int i = 0; i < detailedPost.size(); i++) {
		 publishedDate[i] = detailedPost.get(i).getCreatedTime(); 
		// if (detailedPost.get(i).getLikesCount() != null)
		// numberOfLikes[i] = detailedPost.get(i).getLikesCount().toString(); 
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
		}
		
		for (int i = 0; i < detailedPost.size(); i++) {
			System.out.println("# "+ i ); 
			//System.out.println("Likes: " + numberOfLikes[i]); 
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
			}
		
		
		//activity st = new activity("1", "2", "3", "4", "5", "6");
		activity [] st = new activity[size+1]; 
		for (int k = 0; k < size; k++)
		st[k] = new activity(name, sourceLink[k], "Shawn", locationPosted[k], publishedDate[k].toString(), storyText[k]);

		return st;

	}
	
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response consumeJSON( activity[] student ) {
		String output = "0"; 
		for (int i = 0; i < sizeOfArray; i++ )
		output = output + student[i].toString();

		return Response.status(200).entity(output).build();
	}


}
