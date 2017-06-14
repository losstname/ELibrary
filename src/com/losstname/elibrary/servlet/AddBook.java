package com.losstname.elibrary.servlet;

import com.losstname.elibrary.beans.BookBean;
import com.losstname.elibrary.dao.BookDao;

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
@WebServlet(name = "AddBook")
public class AddBook extends HttpServlet {
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
        request.getRequestDispatcher("navlibrarian.html").include(request,response);
        writer.println("<div class='container'>");
        String callNo = request.getParameter("callno"),
                name = request.getParameter("name"),
                author = request.getParameter("author"),
                publisher = request.getParameter("publisher");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BookBean bookBean = new BookBean(callNo,name,author,publisher,quantity);
        if(BookDao.save(bookBean)>0){
            writer.println("<h3>Book saved successfully</h3>");
        }
        request.getRequestDispatcher("addbookform.html").include(request,response);
        writer.println("</div>");
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
