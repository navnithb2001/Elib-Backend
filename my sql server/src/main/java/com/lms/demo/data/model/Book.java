package com.lms.demo.data.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "book")
public class Book {
  @Id
  private String ISBN;
  
  private String title;
 
  private String cover;

  private String publisher;

  private Long pages;

  private Long available;
  private Long rating;

  @JsonManagedReference(value = "book")
  @OneToMany(mappedBy = "book")
  private List<Order> orders;

  
  
  public Long getRating() {
    return rating;
  }

  public void setRating(Long rating) {
    this.rating = rating;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String iSBN) {
    ISBN = iSBN;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCover() {
    return cover;
  }

  public void setCover(String cover) {
    this.cover = cover;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  

  public Long getPages() {
    return pages;
  }

  public void setPages(Long pages) {
    this.pages = pages;
  }

  public Long getAvailable() {
    return available;
  }

  public void setAvailable(Long available) {
    this.available = available;
  }

  

  @Override
  public String toString() {
    return "Book [ISBN=" + ISBN + ", available=" + available + ", cover=" + cover + ", orders=" + orders + ", pages="
        + pages + ", publisher=" + publisher + ", title=" + title + "]";
  }

  

  public Book(String iSBN, String title, String cover, String publisher, Long pages, Long available, Long rating,
      List<Order> orders) {
    ISBN = iSBN;
    this.title = title;
    this.cover = cover;
    this.publisher = publisher;
    this.pages = pages;
    this.available = available;
    this.rating = rating;
    this.orders = orders;
  }

  public Book() {
  }

}
