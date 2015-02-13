package org.sample.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: hasini
 * Date: 2/12/15
 * Time: 8:44 PM
 */

/**
 * This is a sample client which communicates with the backend REST service via HTTP methods.
 * This uses the apache-httpclient library.
 */
public class HelloClient {
    private static String serviceURL = "http://localhost:8080/HelloWebApp/service/hello";
    private static String firstName = "Hasini";
    private static String lastName = "Gunasinghe";

    public static void main(String[] args) throws IOException, JSONException {

        /**********send a POST request to the HelloRESTService in order to invoke the "greet" method.***********/

        /**encode the request message in JSON which is in the following format
         {
         "firstName":"ABC",
         "lastName":"XYZ"
         }
         **/
        JSONObject request = new JSONObject();
        request.put("firstName", firstName);
        request.put("lastName", lastName);

        //create http client
        HttpClient client = new HttpClient();

        PostMethod postMethod = new PostMethod(serviceURL);
        RequestEntity requestEntity = new StringRequestEntity(request.toString(), Constants.CONTENT_TYPE, Constants.CHAR_SET);
        postMethod.setRequestEntity(requestEntity);

        int status = client.executeMethod(postMethod);
        String response = postMethod.getResponseBodyAsString();

        //print the full response string
        System.out.println("Response: " + response);

        //decode the greeting message from the JSON response
        JSONObject respObj = new JSONObject(new JSONTokener(response));
        String greetingMsg = respObj.optString("greetingMessage");

        //print the greetings message
        System.out.println("Greeting Message: " + greetingMsg);
    }
}
