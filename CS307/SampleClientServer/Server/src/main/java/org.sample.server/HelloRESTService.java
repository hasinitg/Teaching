package org.sample.server;

/**
 * Created with IntelliJ IDEA.
 * User: hasini
 * Date: 2/12/15
 * Time: 6:50 PM
 */

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

/**
 * This is the class that implements the REST API for HelloService. It exposes methods related to different
 * functionality of the service which are mapped to four main HTTP methods using annotations.
 */
public class HelloRESTService {
    @POST
    public Response greet(String message) {
        try {
            /*****process user's message
             we expect the message to be in the following format:
             {
             "firstName":"ABC",
             "lastName":"XYZ"
             }*********/
            //decode the JSON message
            JSONObject request = new JSONObject(new JSONTokener(message));
            String firstName = request.optString("firstName");
            String lastName = request.optString("lastName");

            //create response status and response string
            StringBuilder response = new StringBuilder();
            response.append("Hello ");
            response.append(firstName);
            response.append("!!!");

            //encode response in a JSON message
            JSONObject responseObj = new JSONObject();
            responseObj.put("greetingMessage", response.toString());

            //create JAXRS response and return
            Response.ResponseBuilder responseBuilder = Response.status(Constants.HTTP_OK);
            responseBuilder.entity(responseObj.toString());
            return responseBuilder.build();
        } catch (JSONException e) {
            //if an error occurred, return the resposne with error message.
            e.printStackTrace();
            Response.ResponseBuilder responseBuilder = Response.status(Constants.HTTP_ERROR);
            responseBuilder.entity("Error occurred while processing the message.");
            return responseBuilder.build();
        }
    }


}
