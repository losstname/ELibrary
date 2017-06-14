package com.losstname.elibrary.servlet;

import com.losstname.elibrary.beans.LibrarianBean;
import com.losstname.elibrary.dao.LibrarianDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by losstname on 5/2/17.
 */
@WebServlet(name = "EditLibrarianForm")
public class EditLibrarianForm extends HttpServlet {
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
        request.getRequestDispatcher("navadmin.html").include(request,response);
        writer.println("<div class='container'>");
        int id = Integer.parseInt(request.getParameter("id"));
        LibrarianBean librarianBean = LibrarianDao.viewById(id);
        writer.println("<form action=\"editlibrarian\" method=\"post\" style=\"width:300px\">\n" +
                "    <div class=\"form-group\">\n" +
                "        <input type=\"hidden\" name=\"id\" id=\"id\" value=\""+id+"\"> " +
                "        <label for=\"name\">Name</label>\n" +
                "        <input type=\"text\" class=\"form-control\" name=\"name\" id=\"name\" placeholder=\"Name\"/>\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label for=\"email\">Email address</label>\n" +
                "        <input type=\"email\" class=\"form-control\" name=\"email\" id=\"email\" placeholder=\"Email\"/>\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label for=\"password\">Password</label>\n" +
                "        <input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\" placeholder=\"Password\"/>\n" +
                "    </div>\n" +
                "    <div class=\"form-group\">\n" +
                "        <label for=\"mobile\">Mobile Number</label>\n" +
                "        <input type=\"number\" class=\"form-control\" name=\"mobile\" id=\"mobile\" placeholder=\"Mobile\"/>\n" +
                "    </div>\n" +
                "    <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\n" +
                "</form>");
        writer.println("</div>");
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }
}
