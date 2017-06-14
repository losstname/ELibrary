package com.losstname.elibrary.servlet;

import com.losstname.elibrary.beans.IssuedBookBean;
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
@WebServlet(name = "ViewIssuedBook")
public class ViewIssuedBook extends HttpServlet {
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
        List<IssuedBookBean> issuedBookBeans = BookDao.viewIssuedBooks();
        writer.println("<table class='table table-bordered table-striped'>" +
                "<tr><th>Call No</th><th>Student Id</th><th>Student Name</th><th>Student Mobile</th><th>Issued Date</th><th>Return Status</th></tr>");
        for (IssuedBookBean issuedBookBean : issuedBookBeans){
            writer.println("<tr>" +
                    "<td>"+issuedBookBean.getCallNo()+"</td>" +
                    "<td>"+issuedBookBean.getStudentId()+"</td>" +
                    "<td>"+issuedBookBean.getStudentName()+"</td>" +
                    "<td>"+issuedBookBean.getStudentMobile()+"</td>" +
                    "<td>"+issuedBookBean.getIssuedDate()+"</td>" +
                    "<td>"+issuedBookBean.getReturnStatus()+"</a></td>");
        }
        writer.println("</table>" +
                "</div>");
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }
}
