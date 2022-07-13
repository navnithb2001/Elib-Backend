package com.elib.reviews.controller;

import java.util.List;

import com.elib.reviews.model.Rating;
import com.elib.reviews.repository.RatingRepository;
import com.elib.reviews.service.SequenceGeneratorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class RatingController {
    
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/rating")
    public List<Rating> getRating() {
        return ratingRepository.findAll();
    }

    @GetMapping("/rating/{bookid}")
    public Long getRatingBybookid(@PathVariable String bookid) {
        List<Rating> ratList =  ratingRepository.findByBookid(bookid);
        if(ratList.size() == 0){
            return (long) 0;
        }
        Long avg = (long) 0;
        for(Rating x: ratList){
            avg += x.getRating();
        }
        return avg/ratList.size();
    }

    @PostMapping("/addRating")
    public void addRating(@RequestBody Rating rat) {
        rat.setRating_id(sequenceGeneratorService.generateSequence(rat.SEQUENCE_NAME));
        ratingRepository.save(rat);
    }
}
