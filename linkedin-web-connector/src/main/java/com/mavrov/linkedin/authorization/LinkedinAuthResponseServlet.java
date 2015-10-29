package com.mavrov.linkedin.authorization;

import com.mavrov.repository.ProfileRepository;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author serg.mavrov@gmail.com
 */
public class LinkedinAuthResponseServlet extends HttpServlet {

    @Inject
    private ProfileRepository profileRepo;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //-=-=-
        if (request.getParameter("code") != null) {
            Client client = Client.create();
            WebResource webResourcePost =
                    client.resource("https://www.linkedin.com/uas/oauth2/accessToken");
            MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
            formData.add("client_id", "774g998g1hvui6");
            formData.add("client_secret", "12WaBJlQxbSndcSI");
            formData.add("grant_type", "authorization_code");
            formData.add("code", request.getParameter("code"));
            formData.add("redirect_uri", "http://mavrov.de:28080/linkedin-web-connector/linkedin-auth-response");
            ClientResponse postResponse =
                    webResourcePost.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, formData);
            if (postResponse.getStatus() != 200) {
                throw new IllegalStateException("Failed to get access code : " + postResponse.getStatus());
            }
            //-=-=- send the profile request to the linkedin api server
            String accessTokenRes = postResponse.getEntity(String.class);
            String[] accessParts = accessTokenRes.split(",");
            String[] accessTokenParts = accessParts[0].split(":");
            String[] clearAccessToken = accessTokenParts[1].split("\"");
            String appKey = clearAccessToken[1];
            //-=-=-=-
            if (appKey != null) {
                WebResource webResource = client
                        .resource("https://api.linkedin.com/v1/people/~:(email-address,first-name,last-name,headline)?format=json");
                ClientResponse getResponse = webResource.
                        header("Connection", "Keep-Alive").
                        header("Authorization", "Bearer " + appKey).
                        accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
                if (getResponse.getStatus() != 200) {
                    throw new IllegalStateException("Failed to get the profile : " + getResponse.getStatus());
                }
                String textProfile = getResponse.getEntity(String.class);
                out.println("<h1>PROFILE</h1><br/>");
                out.println("<h1>" + textProfile + "</h1>");

            }
            // -=-=-
        }

    }

}
