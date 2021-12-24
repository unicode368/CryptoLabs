package com.example.lab5.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

}
