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
import java.util.List;

/**
 * Created by losstname on 6/8/17.
 */
@WebServlet(name = "ViewLibrarian")
public class ViewLibrarian extends HttpServlet {
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
        List<LibrarianBean> librarianBeans = LibrarianDao.view();
        writer.println("<table class='table table-bordered table-striped'>" +
                "<tr><th>Id</th><th>Name</th><th>Email</th><th>Password</th><th>Mobile</th><th>Edit</th><th>Delete</th></tr>");
        for (LibrarianBean librarianBean:librarianBeans){
            writer.println("<tr>" +
                    "<td>"+librarianBean.getId()+"</td>" +
                    "<td>"+librarianBean.getName()+"</td>" +
                    "<td>"+librarianBean.getEmail()+"</td>" +
                    "<td>"+librarianBean.getPassword()+"</td>" +
                    "<td>"+librarianBean.getMobile()+"</td>" +
                    "<td><a href='editlibrarianform?id="+librarianBean.getId()+"'>Edit</a></td>" +
                    "<td><a href='deletelibrarian?id="+librarianBean.getId()+"'>Delete</a></td>");
        }
        writer.println("</table>" +
                "</div>");
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }
}
