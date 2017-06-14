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

/**
 * Created by losstname on 5/7/17.
 */
@WebServlet(name = "IssueBook")
public class IssueBook extends HttpServlet {
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
                studentId = request.getParameter("studentid"),
                studentName = request.getParameter("studentname");
        long studentMobile = Long.parseLong(request.getParameter("studentmobile"));
        IssuedBookBean issuedBookBean = new IssuedBookBean(callNo,studentId,studentName,studentMobile);
        if(BookDao.issueBook(issuedBookBean)>0){
            writer.println("<h3>Book issued successfully</h3>");
        }else{
            writer.println("<h3>Sorry, unable to issue book.</h3><p>We may have sortage of books. Kindly visit later.</p>");
        }
        writer.println("</div>");
        request.getRequestDispatcher("footer.html").include(request,response);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
