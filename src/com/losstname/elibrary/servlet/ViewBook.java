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
import java.util.List;

/**
 * Created by losstname on 5/7/17.
 */
@WebServlet(name = "ViewBook")
public class ViewBook extends HttpServlet {
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
        List<BookBean> bookBeans = BookDao.view();
        writer.println("<table class='table table-bordered table-striped'>" +
                "<tr><th>Call No</th><th>Name</th><th>Author</th><th>Publisher</th><th>Quantity</th><th>Issued</th><th>Delete</th></tr>");
        for (BookBean bookBean: bookBeans){
            writer.println("<tr>" +
                    "<td>"+bookBean.getcallNo()+"</td>" +
                    "<td>"+bookBean.getName()+"</td>" +
                    "<td>"+bookBean.getAuthor()+"</td>" +
                    "<td>"+bookBean.getPublisher()+"</td>" +
                    "<td>"+bookBean.getQuantity()+"</td>" +
                    "<td>"+bookBean.getIssued()+"</td>" +
                    "<td><a href='deletebook?callno="+ bookBean.getcallNo()+"'>Delete</a></td>");
        }
        writer.println("</table>" +
                "</div>");
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }
}
