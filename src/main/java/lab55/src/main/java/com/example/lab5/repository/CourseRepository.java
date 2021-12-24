package com.example.lab5.repository;


import com.example.lab5.entity.Course;
import com.example.lab5.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Optional<List<Course>> findByTheme(String theme);
    Optional<List<Course>> findByTeacher(User teacher);
}
