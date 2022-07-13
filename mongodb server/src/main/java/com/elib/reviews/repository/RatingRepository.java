package com.elib.reviews.repository;

import java.util.List;

import com.elib.reviews.model.Rating;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends MongoRepository<Rating,Long> {
    
    public List<Rating> findByBookid(String bookid);
}
