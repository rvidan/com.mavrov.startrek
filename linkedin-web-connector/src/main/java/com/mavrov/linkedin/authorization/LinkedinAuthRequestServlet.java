package com.mavrov.linkedin.authorization;

import com.mavrov.connector.linkedin.AccessScope;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author serg.mavrov@gmail.com
 */
public class LinkedinAuthRequestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //redirect to the linkedin site
        response.sendRedirect("https://www.linkedin.com/uas/oauth2/authorization?"
                        + "response_type=code"
                        + "&client_id=774g998g1hvui6"
                        + "&redirect_uri=http://mavrov.de:28080/linkedin-web-connector/linkedin-auth-response"
                        + "&state=777"
                        + "&scope" + AccessScope.r_basicprofile.name()
        );

    }

}
