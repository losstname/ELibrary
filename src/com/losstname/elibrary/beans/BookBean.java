package com.losstname.elibrary.beans;

/**
 * Created by losstname on 6/5/17.
 */
public class BookBean {
    private String callNo,
        name,
        author,
        publisher;
    private int quantity,
        issued;

    public BookBean(){
        super();
    }

    public BookBean(String callNo, String name, String author, String publisher, int quantity){
        super();
        this.callNo = callNo;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public String getcallNo(){
        return callNo;
    }

    public void setcallNo(String callNo){
        this.callNo = callNo;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getIssued(){
        return issued;
    }

    public void setIssued(int issued){
        this.issued = issued;
    }
}
