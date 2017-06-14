package com.losstname.elibrary.servlet;

import com.losstname.elibrary.dao.LibrarianDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by losstname on 5/5/17.
 */
@WebServlet(name = "LibrarianLogin")
public class LibrarianLogin extends HttpServlet {
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
        String email = request.getParameter("email"),
                password = request.getParameter("password");
        if (LibrarianDao.authenticate(email,password)){
            HttpSession session = request.getSession();
            session.setAttribute("email",email);

            request.getRequestDispatcher("navlibrarian.html").include(request,response);
            request.getRequestDispatcher("librariancarousel.html").include(request,response);
        }else{
            request.getRequestDispatcher("navhome.html").include(request,response);
            writer.println("<div class='container'>" +
                    "<h3>Username or password error");
            request.getRequestDispatcher("librarianloginform.html").include(request,response);
            writer.println("</div>");
        }
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>ELibrary</title>\n" +
                "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>\n" +
                "</head>\n" +
                "<body>");
        if(session.getAttribute("email") == null){
            request.getRequestDispatcher("navhome.html").include(request,response);
            writer.println("<div class='container'>" +
                    "<h3>You aren't login yet");
            request.getRequestDispatcher("librarianloginform.html").include(request,response);
            writer.println("</div>");
        }else{
            request.getRequestDispatcher("navlibrarian.html").include(request,response);
            request.getRequestDispatcher("librariancarousel.html").include(request,response);
        }
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }
}
