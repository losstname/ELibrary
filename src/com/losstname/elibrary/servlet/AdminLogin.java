package com.losstname.elibrary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by losstname on 6/5/17.
 */
@WebServlet(name = "AdminLogin")
public class AdminLogin extends HttpServlet {
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

        if (email.equals("admin@email") && password.equals("adminadmin")){
            HttpSession session = request.getSession();
            session.setAttribute("admin",true);
            request.getRequestDispatcher("navadmin.html").include(request,response);
            request.getRequestDispatcher("admincarousel.html").include(request,response);

        }else {
            request.getRequestDispatcher("navhome.html").include(request,response);
            writer.println("<div class='container'>\n" +
                    "<h3>Username or password error</h3>");
            request.getRequestDispatcher("adminloginform.html").include(request,response);
            writer.println("</div>");
        }
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean islogin= (Boolean)session.getAttribute("admin");
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>ELibrary</title>\n" +
                "    <link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>\n" +
                "</head>\n" +
                "<body>");
        if (islogin){
            request.getRequestDispatcher("navadmin.html").include(request,response);
            request.getRequestDispatcher("admincarousel.html").include(request,response);
        }else{
            request.getRequestDispatcher("navhome.html").include(request,response);
            writer.println("<div class='container'>\n" +
                    "<h3>Username or password error</h3>");
            request.getRequestDispatcher("adminloginform.html").include(request,response);
            writer.println("</div>");
        }
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }
}
