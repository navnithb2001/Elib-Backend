package com.elib.reviews.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rating")
public class Rating {

    @Transient
    public static final String SEQUENCE_NAME = "rating_sequence";
    
    @Id
    private Long rating_id;
    private Long rating;
    private String bookid;
    private Long userid;

    
    public Long getRating_id() {
        return rating_id;
    }
    public void setRating_id(Long rating_id) {
        this.rating_id = rating_id;
    }
    public Long getRating() {
        return rating;
    }
    public void setRating(Long rating) {
        this.rating = rating;
    }
    public String getBookid() {
        return bookid;
    }
    public void setBookid(String bookid) {
        this.bookid = bookid;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    
    public Rating(Long rating, String bookid, Long userid) {
        this.rating = rating;
        this.bookid = bookid;
        this.userid = userid;
    }
    
    public Rating(Long rating_id, Long rating, String bookid, Long userid) {
        this.rating_id = rating_id;
        this.rating = rating;
        this.bookid = bookid;
        this.userid = userid;
    }
    public Rating() {
    }
}
