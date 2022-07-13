package com.lms.demo.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class User {

  @Id
  @SequenceGenerator(
         name = "user_sequence",
         sequenceName = "user_sequence",
         allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
  private Long userid;
  
  private String username;
  private String password;
  private String name;
  private Date dob;
  private String address;
  private Long fine;

  @Enumerated(EnumType.STRING)
  private Roles role;

  @JsonManagedReference(value = "user")
  @OneToMany(mappedBy = "user")
  private List<Order> order;

  

  public Long getFine() {
    return fine;
  }


  public void setFine(Long fine) {
    this.fine = fine;
  }


  public Long getUserId() {
    return userid;
  }

  
  public String getName() {
    return name;
  }



  public void setName(String name) {
    this.name = name;
  }



  public Date getDob() {
    return dob;
  }



  public void setDob(Date dob) {
    this.dob = dob;
  }



  public String getAddress() {
    return address;
  }



  public void setAddress(String address) {
    this.address = address;
  }



  public List<Order> getOrder() {
    return order;
  }



  public void setOrder(List<Order> order) {
    this.order = order;
  }


  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public Roles getRole() {
    return role;
  }


  public void setRole(Roles role) {
    this.role = role;
  }


  public User(String username, String password, String name, Date dob, String address, Roles role,
      ArrayList<Order> order) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.dob = dob;
    this.address = address;
    this.role = role;
    this.order = order;
    this.fine = (long) 0;
  }


  public User() {
  }

}
