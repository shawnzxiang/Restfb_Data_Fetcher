# AggieFeed

This project can print and send data to a server, such as message, published dates and indicators of popularity in JSON format for any given public facebook page. 

The project is designed to be compatible with Aggie Feed rest API. (https://ucdavis.jira.com/wiki/display/IETP/UC+Davis+Aggie+Feed)

Aggiefeed website: https://aggiefeed.ucdavis.edu/

Run instruction:
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

For example, college of engineering at UC Davis (https://www.facebook.com/UCDEngineering) has "UCDEngineering"
and IEEE uc davis branch(https://www.facebook.com/ieeeucdavis) has "ieeeucdavis"

Credit: Thanks to awesome tutorials by restfb.com and codegeeks.
