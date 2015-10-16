package com.mavrov.linkedin.authorization;

import com.mavrov.connector.linkedin.AccessScope;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

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
                    webResourcePost.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                            .post(ClientResponse.class, formData);
            out.println(postResponse.getEntity(String.class));
            if (postResponse.getStatus() != 200) {
                throw new IllegalStateException("Failed GET: HTTP error code : "
                        + postResponse.getStatus());
            }
        }
        //-=-=-
        if (request.getParameter("access_token") != null) {
            request.getParameterMap().keySet().stream().forEach(
                    parameter ->
                            out.println("<h1>" + request.getParameter(parameter) + "</h1>")
            );
        }
    }

}
