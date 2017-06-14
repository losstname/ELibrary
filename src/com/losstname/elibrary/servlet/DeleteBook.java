package com.losstname.elibrary.servlet;

import com.losstname.elibrary.beans.BookBean;
import com.losstname.elibrary.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by losstname on 5/7/17.
 */
@WebServlet(name = "DeleteBook")
public class DeleteBook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDao.delete(request.getParameter("callno"));
        response.sendRedirect("viewbook");
    }
}
