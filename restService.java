// Written by Shawn

package communicate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class restService {
    @POST
    @Path("/rest")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rest(InputStream incomingData) {
        StringBuilder buildContent = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = in.readLine()) != null) {
                buildContent.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
        }
        System.out.println("Data Received: " + buildContent.toString());
 
        // return HTTP response 200 in case of success
        return Response.status(200).entity(buildContent.toString()).build();
    }
}
