package com.losstname.elibrary.servlet;

import com.losstname.elibrary.beans.LibrarianBean;
import com.losstname.elibrary.dao.DatabaseConnection;
import com.losstname.elibrary.dao.LibrarianDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by losstname on 6/8/17.
 */
@WebServlet(name = "AddLibrarian")
public class AddLibrarian extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        long mobile = Long.parseLong(request.getParameter("mobile"));
        LibrarianBean librarianBean = new LibrarianBean(name,email,password,mobile);
       if(LibrarianDao.save(librarianBean)>0){
            writer.println("<h4>Librarian successfully added</h4>");
       }else{
            writer.println("<h4>Librarian failed to add</h4>");
       }

        request.getRequestDispatcher("addlibrarianform.html").include(request,response);
        writer.println("</div>");
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
