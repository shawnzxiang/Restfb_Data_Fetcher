// Written by Shawn Xiang


import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.lang.Long; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


public class Main {
	
	static String ACCESS_TOKEN = "CAACEdEose0cBAMc9m6xZAGtiaksyv5UySk55sS8eKIFRKNP9qcact52OX6iajIkbZBzSZA9ZCOZBtfBJfwDCrNdqVbrhOrJuNQQTnRzbwGQiFjdbnB8z7LgFpAYZC7ZAkdGmsAvTfw1DnUOjZCYpCVogScU0JK4DZBSih1jCnPwOim0ST1B064DeJZAlTgctZBgJt2wgvpZClJJtGLE8B7fqAqZAPH47C053W6ecZD"; 
	public static void main(String[] args) {
		FacebookClient fbclient = new DefaultFacebookClient(ACCESS_TOKEN, Version.VERSION_2_3); 

		Connection<Post> message = fbclient.fetchConnection("UCDEngineering/feed", com.restfb.types.Post.class,  Parameter.with("until","3/1/2015" ), Parameter.with("to", "1/1/2014"), Parameter.with("limit", 100)); 
		
		List<Post> detailedPost = message.getData(); 
		
		int size = detailedPost.size(); 
		
		Date[] publishedDate = new Date[size]; 
		String[] numberOfLikes = new String[size]; 
		String[] pictureLink = new String[size]; 
		String[] linkCaption = new String[size]; 
		String[] linkDescription = new String[size]; 
		String[] externalLink = new String[size]; 
		String[] sourceLink = new String[size]; 
		String[] typeOfSourceLink = new String[size]; 
		Date [] lastCommented = new Date[size]; 
		String[] storyText = new String[size]; 
		String [] locationPosted = new String[size]; 
		
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
	}
	
	
	
	

}