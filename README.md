# AggieFeed

This project can print and send data to a server, such as message, published dates and indicators of popularity in JSON format for database and string format for any public facebook page. 

The project is designed to be compatible with Aggie Feed rest API. (https://ucdavis.jira.com/wiki/display/IETP/UC+Davis+Aggie+Feed)

Aggiefeed website: 
https://aggiefeed.ucdavis.edu/

Run instruction

You will need the following .jar files:

Restfb v1.11.0

Jackson v1.8.3

Jersey v1.9

I tested the project in Eclipse (maven project) with Tomcat 8.0

After everything is installed, you can run the program using: GET
http://localhost:8080/JerseyJSONExample/rest/jsonServices/print/*

or/and then POST

http://localhost:8080/JerseyJSONExample/rest/jsonServices/send

where * is a public facebook page ID. You can find it after https://www.facebook.com/*

For example, college of engineering at UC Davis (https://www.facebook.com/UCDEngineering) would be
http://localhost:8080/JerseyJSONExample/rest/jsonServices/print/UCDEngineering

and Game of Thrones Facebook page would be 
http://localhost:8080/JerseyJSONExample/rest/jsonServices/print/GameOfThrones

Special thanks to awesome tutorials by restfb.com, CBroe from StackOverflow and codegeeks.
