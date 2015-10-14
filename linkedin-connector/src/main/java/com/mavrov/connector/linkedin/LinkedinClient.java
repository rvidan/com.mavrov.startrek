package com.mavrov.connector.linkedin;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * @author serg.mavrov@gmail.com
 */
@ApplicationScoped
public class LinkedinClient implements ConnectorClient {

    @Inject
    private transient Logger logger;

    @Override
    public String authorize() {

        Client client = Client.create();

        WebResource webResourceGet = client.resource("https://www.linkedin.com/uas/oauth2/authorization");
        ClientResponse getResponse = webResourceGet
                .queryParam("response_type", "code")
                .queryParam("client_id", "774g998g1hvui6")
                .queryParam("redirect_uri", "http://mavrov.com")
                .queryParam("state", "777")
                .queryParam("scope", AccessScope.r_basicprofile.name())
                .get(ClientResponse.class);

        if (getResponse.getStatus() != 200) {
            throw new IllegalStateException("Failed authorization: HTTP error code : " + getResponse.getStatus());
        }

        String output = getResponse.getEntity(String.class);
        logger.info(output);
        return output;

//            WebResource webResourcePost = client.resource("https://www.linkedin.com/uas/oauth2/authorization");
//            MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
//            formData.add("client_id", "774g998g1hvui6");
//            formData.add("client_secret", "12WaBJlQxbSndcSI");
//            ClientResponse postResponse =
//                    webResourcePost.type(MediaType.
//                            APPLICATION_FORM_URLENCODED_TYPE)
//                            .post(ClientResponse.class, formData);
//
//            if (postResponse.getStatus() != 200) {
//                throw new RuntimeException("Failed GET: HTTP error code : "
//                        + postResponse.getStatus());
//            }
//
//            String output = postResponse.getEntity(String.class);
//            System.out.println("Output from Server .... \n");
//            System.out.println(output);

    }

}
