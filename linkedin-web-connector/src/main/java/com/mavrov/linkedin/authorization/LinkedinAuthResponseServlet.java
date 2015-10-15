package com.mavrov.linkedin.authorization;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author serg.mavrov@gmail.com
 */
public class LinkedinAuthResponseServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        //-=-=-
        PrintWriter out = response.getWriter();
        request.getParameterMap().keySet().stream().forEach(
                parameter ->
                        out.println("<h1>" + request.getParameter(parameter) + "</h1>")
        );
    }

}
