package com.losstname.elibrary.servlet;

import com.losstname.elibrary.beans.LibrarianBean;
import com.losstname.elibrary.dao.LibrarianDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by losstname on 5/2/17.
 */
@WebServlet(name = "EditLibrarian")
public class EditLibrarian extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name"),
                email = request.getParameter("email"),
                password = request.getParameter("password");
        long mobile = Long.parseLong(request.getParameter("mobile"));
        LibrarianBean librarianBean = new LibrarianBean(id,name,email,password,mobile);
        LibrarianDao.update(librarianBean);
        response.sendRedirect("viewlibrarian");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
