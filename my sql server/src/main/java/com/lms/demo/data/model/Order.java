package com.lms.demo.data.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "order_details")
public class Order implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy = "uuid")
  private String booking_id;

  @JsonBackReference(value = "user")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
  
  private Date booking_date;
  private Date return_date;

  @Transient
  private Boolean isOverdue;

  @JsonBackReference(value = "book")
  @ManyToOne(fetch = FetchType.LAZY)
  private Book book;


  

  public Boolean getIsOverdue() {
    if(Date.valueOf(LocalDate.now()).compareTo(return_date) > 0){
      isOverdue = true;
    }
    else{
      isOverdue = false;
    }
    return isOverdue;
  }

  public String getBooking_id() {
    return booking_id;
  }

  

public Date getReturn_date() {
  return return_date;
}



public void setReturn_date(Date return_date) {
  this.return_date = return_date;
}



  public Book getBook() {
    return book;
  }



  public void setBook(Book book) {
    this.book = book;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getBooking_date() {
    return booking_date;
  }

  public void setBooking_date(Date booking_date) {
    this.booking_date = booking_date;
  }

  public Order(Date booking_date, Date return_date) {
    this.booking_date = booking_date;
    this.return_date = return_date;
  }



  public Order() {
  }
    
  
}
