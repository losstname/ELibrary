package com.losstname.elibrary.dao;

import com.losstname.elibrary.beans.BookBean;
import com.losstname.elibrary.beans.IssuedBookBean;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by losstname on 6/5/17.
 */
public class BookDao {
    public static int save(BookBean bookBean){
        int status = 0;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO book VALUES (?,?,?,?,?,?)");
            statement.setString(1, bookBean.getcallNo());
            statement.setString(2, bookBean.getName());
            statement.setString(3, bookBean.getAuthor());
            statement.setString(4, bookBean.getPublisher());
            statement.setInt(5, bookBean.getQuantity());
            statement.setInt(6, 0);
            status = statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static List<BookBean> view(){
        List<BookBean> bookBeanList = new ArrayList<BookBean>();
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement  = conn.prepareStatement("SELECT * FROM book");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                BookBean bookBean = new BookBean();
                bookBean.setcallNo(resultSet.getString("callno"));
                bookBean.setName(resultSet.getString("name"));
                bookBean.setAuthor(resultSet.getString("author"));
                bookBean.setPublisher(resultSet.getString("publisher"));
                bookBean.setQuantity(resultSet.getInt("quantity"));
                bookBean.setIssued(resultSet.getInt("issued"));
                bookBeanList.add(bookBean);
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookBeanList;
    }

    public static int delete(String callNo){
        int status=0;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM book WHERE callno = ?");
            statement.setString(1,callNo);
            status = statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int getIssued(String callNo){
        int issued = 0;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM book WHERE callno=?");
            statement.setString(1,callNo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                issued = resultSet.getInt("issued");
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issued;
    }

    public static boolean checkIssue(String callNo){
        boolean status = false;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM book WHERE callno=? AND quantity>issued");
            statement.setString(1,callNo);
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

    public static int updateIssue(String callNo){
        int status = 0;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE book SET issued=? WHERE callno=?");
            statement.setInt(1,getIssued(callNo)+1);
            statement.setString(2, callNo);
            status = statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static int issueBook (IssuedBookBean issuedBookBean){
        String callNo = issuedBookBean.getCallNo();
        boolean checkStatus = checkIssue(callNo);
        if (checkStatus){
            int status = 0;
            Connection conn = DatabaseConnection.getConn();
            try {
                PreparedStatement statement = conn.prepareStatement("INSERT INTO issuebook VALUES (?,?,?,?,?,?)");
                statement.setString(1,issuedBookBean.getCallNo());
                statement.setString(2,issuedBookBean.getStudentId());
                statement.setString(3,issuedBookBean.getStudentName());
                statement.setLong(4,issuedBookBean.getStudentMobile());
                Date currDate = new Date(System.currentTimeMillis());
                statement.setDate(5,currDate);
                statement.setString(6,"no");
                status = statement.executeUpdate();
                if (status>0){
                    status = updateIssue(callNo);
                }
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return status;
        }else {
            return 0;
        }
    }

    public static int returnBook(String callNo, int studentId){
        int status = 0;
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE issuebook SET returnstatus=? WHERE callno=? AND studentid=?");
            statement.setString(1,"yes");
            statement.setString(2, callNo);
            statement.setInt(3, studentId);
            status = statement.executeUpdate();
            if (status>0){
                status = updateIssue(callNo);
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static List<IssuedBookBean> viewIssuedBooks(){
        List<IssuedBookBean> issuedBookBeans = new ArrayList<IssuedBookBean>();
        Connection conn = DatabaseConnection.getConn();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM issuebook ORDER BY issueddate DESC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                IssuedBookBean issuedBookBean = new IssuedBookBean();
                issuedBookBean.setCallNo(resultSet.getString("callno"));
                issuedBookBean.setStudentId(resultSet.getString("studentid"));
                issuedBookBean.setStudentName(resultSet.getString("studentname"));
                issuedBookBean.setStudentMobile(resultSet.getLong("studentmobile"));
                issuedBookBean.setIssuedDate(resultSet.getDate("issueddate"));
                issuedBookBean.setReturnStatus(resultSet.getString("returnstatus"));
                issuedBookBeans.add(issuedBookBean);
            }
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issuedBookBeans;
    }
}
