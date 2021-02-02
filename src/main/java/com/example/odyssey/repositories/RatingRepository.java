package com.example.odyssey.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.odyssey.models.Rating;


@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findAll();
}
