package com.losstname.elibrary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by losstname on 5/7/17.
 */
@WebServlet(name = "IssuedBookForm")
public class IssuedBookForm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>ELibrary</title>\n" +
                "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>\n" +
                "</head>\n" +
                "<body>");
        request.getRequestDispatcher("navlibrarian.html").include(request,response);
        writer.println("<div class='container'>");
        request.getRequestDispatcher("issuebookform.html").include(request, response);
        writer.println("</div>");
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }
}
