package com.losstname.elibrary.beans;

import java.util.Date;

/**
 * Created by losstname on 6/5/17.
 */
public class IssuedBookBean {
    private String callNo,
        studentId,
        studentName;
    private long studentMobile;
    private Date issuedDate;
    private String returnStatus;

    public IssuedBookBean(){}

    public IssuedBookBean(String callNo, String studentId, String studentName, long studentMobile){
        super();
        this.callNo = callNo;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMobile = studentMobile;
    }

    public String getCallNo(){
        return callNo;
    }

    public void setCallNo(String callNo){
        this.callNo = callNo;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(long studentMobile) {
        this.studentMobile = studentMobile;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }
}

