package com.calendar;
import groovyjarjarcommonscli.ParseException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
//import java.util.Calendar; 
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.net.URL;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.ValidationException;

import java.util.Map;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.api.transport.PropertyScope;
import org.mule.modules.ObjectStoreModule;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;


/*
 * @author Shawn Xiang (zxiang@ucdavis.edu)
 */


public class readInfo {

	
	
	
	public static void main(String[] args) throws IOException, ValidationException, ParserException {
	

		try {
		InputStream fin;

		// if we were getting data from a file, we might use:
		//is = new FileInputStream("/path/to/file");
			CalendarBuilder builder = new CalendarBuilder();
		// or, from a URL, then retrieve an InputStream from a URL
		//fin = new URL("http://www.trumba.com/calendars/uc-davis-events.ics").openStream();
		//http://www.google.com/calendar/ical/gilsumrocks@gmail.com/public/basic.ics
		fin = new URL("http://www.trumba.com/calendars/uc-davis-events.ics").openStream();
		
		//	FileInputStream fin = new FileInputStream("/Users/zxiang/Desktop/result/3.ics");
		
		//Now Parsing an iCalendar file
		
		//FileInputStream fin = new FileInputStream(is);
		
		Calendar calendar = builder.build(fin); 
	
		// Parse is complete at this point
		
		// Start mapping to activity in BasicDBObjects
		
		Activity activity = new Activity(); 
		
		activity.setVerb("post"); 
		
		BasicDBObject ucdEdusModel = new BasicDBObject();
		
		
		BasicDBObject object = new BasicDBObject();
		
		BasicDBObject objectLocation = new BasicDBObject();
		
		BasicDBObject ucdEdusModelEvent = new BasicDBObject();
		
				for (Iterator i = calendar.getComponents().iterator(); i.hasNext();){
					Component component = (Component) i.next(); 
					System.out.println("---NEW EVENT----"); 
					for (Iterator j = component.getProperties().iterator(); j.hasNext(); ){
						Property property = (Property) j.next(); 
						
						BasicDBObject actor = new BasicDBObject();
						activity.setActor(actor); 
						
						if (property.getName() != null) {
						String nameOfProperty = property.getName(); 
						String valueOfProperty = property.getValue(); 
						
						
						if (true) {
						switch (nameOfProperty) {
						case "SUMMARY":
							BasicDBObject title =  new BasicDBObject(); 
							activity.setTitle(valueOfProperty);
							//System.out.println("Summary: " + valueOfProperty); 
							break; 
						case "LOCATION":
							activity.setObjectLocation(objectLocation);
							activity.setObjectLocationDisplayName(valueOfProperty);
							//System.out.println("Location: " + valueOfProperty); 
						case "DTSTART":
							//System.out.println("Start Date: " + valueOfProperty); 
							activity.setObjectUcdEdusModelEventStartDate(valueOfProperty); 
							break; 
						case "DTEND":
							//System.out.println("End Date: " + valueOfProperty); 
							activity.setObjectUcdEdusModelEventEndDate(valueOfProperty); 
							break; 
						case "X-MICROSOFT-CDO-ALLDAYEVENT": // Can either be "TRUE" or "FALSE"
							//System.out.println("All Day event? " + valueOfProperty); 
							break; 
						case "URL": 
							//System.out.println("URL: " + valueOfProperty); 
							activity.setObjectUcdEdusModelUrl(valueOfProperty); 
							activity.setObjectUcdEdusModelUrlDisplayName("Linked to the event"); 
							break; 
						case "DTSTAMP": 
							//System.out.println("Date stamp: " + valueOfProperty); 
							break; 
						case "DESCRIPTION": 
							//System.out.println("Description: " + valueOfProperty); 
							activity.setObjectContent(valueOfProperty); 
							break; 
						case "UID": 
							//System.out.println("UID: " + valueOfProperty); 
							break;
						case "CATEGORIES":
							//System.out.println("Categories: " + valueOfProperty); 
							break; 
							
						default:
							//System.out.println("DEFAULT/" + nameOfProperty + valueOfProperty);
							String [] ss = property.getParameters().toString().split("=|\\;"); 
							
							int index = 0;
							for (String token: ss){
								
								if (token.contains("NAME")){
								//System.out.println("NAME " + ss[index + 1]); 
									if (ss[index+1] == "Presented by") {
										BasicDBObject author = new BasicDBObject();
										activity.setActorAuthor(author);
										activity.setActorDisplayName(valueOfProperty); 
									}
								} else if (token.contains("ID")){
									//System.out.println("ID " + ss[index + 1]); 
								} else if (token.contains("TYPE")) {
									//System.out.println("Type " + ss[index +1]); 
								}
								else {
								//	System.out.println("Not detected " + token ); 
								} // else
								
								index++; 
								
							}// for (string token : ss)
							
							
							break; 
						}// case
						
						
						
						
							
						//System.out.println(property.getValue()); 
						}// if 
						
						else {
							//System.out.println("Parameters" + property.getParameters()); 
						} // else 
						
					}// for 
						
						}
					if (object != null){
						activity.setObject(object); 
						//System.out.println(object); 
						}
						if (ucdEdusModel != null){
						activity.setObjectUcdEdusModel(ucdEdusModel);
						//System.out.println(ucdEdusModel); 
						}
						if (objectLocation != null){
						activity.setObjectLocation(objectLocation); 
						//System.out.println(objectLocation); 
						}
						if (ucdEdusModelEvent != null){
						activity.setObjectUcdEdusModelEvent(ucdEdusModelEvent);
						//System.out.println(ucdEdusModelEvent); 
						}
						System.out.println(""); 
						System.out.println(object); 
				}// for 
				
				/*
				 * Documentation for the format
				 * 1. Component [VEVENT]
				 * 2. The summary or title (SUMMARY)
				 * 3. The start date (DTSTART)
				 * 4. The end date (DTEND)
				 * 5. is it all day event (X-MICROSOFT-CDO-ALLDAYEVENT)
				 * 6. URL 
				 * 7. /NAME (X-TRUMBA-CUSTOMFIELD)
				 * 8. /ID (X-TRUMBA-CUSTOMFIELD)
				 * 9. /TYPE (X-TRUMBA-CUSTOMFIELD)
				 * 10. /FIELD (X-TRUMBA-CUSTOMFIELD)
				 * 11. Data stamp / time (DTSTAMP)
				 * 12. DESCRIPTION
				 * 13. CATEGORIES
				 * 14: UID
				 */
		/*
		BasicDBObject activity = new BasicDBObject();
		activity.append("verb", "post");
		
		
		
		
		BasicDBObject actor = new BasicDBObject("displayName", calendarName).append("id", calendarId);
		
		activity.append("actor", actor);
		BasicDBObject image = new BasicDBObject("icon", "/icon-rss");
		
		BasicDBObject ucdEdusModel = new BasicDBObject("location", 
					new BasicDBObject("coords", (new BasicDBList()).add(new BasicDBObject("coord1", 1));
		*/
		
					
					
		} // outer for loop
		catch (ParserException e) {
			System.out.println("ParseFailed" + e); 
		}
		
		
		
		
		
		}// function
		
	
}// class readInfo
