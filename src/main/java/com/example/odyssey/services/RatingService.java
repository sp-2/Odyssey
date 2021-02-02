package com.example.odyssey.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.odyssey.models.Rating;
import com.example.odyssey.repositories.RatingRepository;

@Service
public class RatingService {
	// adding the rating repository as a dependency
    private final RatingRepository ratingRepository;
    
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }
    
    // returns all the ratings
    public List<Rating> allRatings() {
        return ratingRepository.findAll();
    }
    
    // creates a rating
    public Rating createRating(Rating b) {
        return ratingRepository.save(b);
    }
    
    // retrieves a rating
    public Rating findRating(Long id) {
        Optional<Rating> optionalRating = ratingRepository.findById(id);
        if(optionalRating.isPresent()) {
            return optionalRating.get();
        } else {
            return null;
        }
    }
   
    
    // updates a rating
    public Rating updateRating(Long id, int rating) {
        
        Optional<Rating> optionalRating = ratingRepository.findById(id);

        if(optionalRating.isPresent()) {
        	Rating b = optionalRating.get();       	
        	b.setValue(rating);
        	return ratingRepository.save(b);
        } else {
            return null;
        }
    	   	
    }
    
    // updates a rating
    public Rating updateRating(Rating rating) {
        return ratingRepository.save(rating);
    }
    
    // deletes a rating
    public void deleteRating(Long id) {       
        Optional<Rating> optionalRating = ratingRepository.findById(id);
        if(optionalRating.isPresent()) {
            ratingRepository.deleteById(id);
        } 
    }
}

