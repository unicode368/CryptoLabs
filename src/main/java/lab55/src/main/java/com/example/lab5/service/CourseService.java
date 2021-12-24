package com.example.lab5.service;

import com.example.lab5.entity.Course;
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
