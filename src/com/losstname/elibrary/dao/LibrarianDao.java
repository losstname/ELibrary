package com.losstname.elibrary.dao;

import com.losstname.elibrary.beans.LibrarianBean;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by losstname on 6/5/17.
 */
public class LibrarianDao {
    public static int save(LibrarianBean librarianBean){
        int status=0;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO librarian (name,email,password,mobile)VALUES (?,?,?,?)");
            statement.setString(1,librarianBean.getName());
            statement.setString(2,librarianBean.getEmail());
            statement.setString(3,librarianBean.getPassword());
            statement.setLong(4, librarianBean.getMobile());
            status = statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int update(LibrarianBean librarianBean){
        int status=0;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE librarian SET name=?,email=?,password=?,mobile=? WHERE id=?");
            statement.setString(1,librarianBean.getName());
            statement.setString(2,librarianBean.getEmail());
            statement.setString(3,librarianBean.getPassword());
            statement.setLong(4,librarianBean.getMobile());
            statement.setInt(5,librarianBean.getId());
            status = statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static List<LibrarianBean> view(){
        List<LibrarianBean> librarianBeans = new ArrayList<LibrarianBean>();
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM librarian");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                LibrarianBean librarianBean = new LibrarianBean();
                librarianBean.setId(resultSet.getInt("id"));
                librarianBean.setName(resultSet.getString("name"));
                librarianBean.setEmail(resultSet.getString("email"));
                librarianBean.setPassword(resultSet.getString("password"));
                librarianBean.setMobile(resultSet.getLong("mobile"));
                librarianBeans.add(librarianBean);
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarianBeans;
    }

    public static LibrarianBean viewById(int id){
        LibrarianBean librarianBean = new LibrarianBean();
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM librarian WHERE id=?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                librarianBean.setId(resultSet.getInt("id"));
                librarianBean.setName(resultSet.getString("name"));
                librarianBean.setEmail(resultSet.getString("email"));
                librarianBean.setPassword(resultSet.getString("password"));
                librarianBean.setMobile(resultSet.getLong("mobile"));
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarianBean;
    }

    public static int delete(int id){
        int status=0;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM elibrary.librarian WHERE id=?");
            statement.setInt(1,id);
            status = statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean authenticate(String email, String password){
        boolean status = false;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM librarian WHERE email=? AND password=?");
            statement.setString(1, email);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                status = true;
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
