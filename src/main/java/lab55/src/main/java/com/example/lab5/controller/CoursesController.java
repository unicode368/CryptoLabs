package com.example.lab5.controller;

import com.ua.faculty.dto.CourseDTO;
import com.ua.faculty.entity.Course;
import com.ua.faculty.exceptions.StudentAlreadyEnrolledException;
import com.ua.faculty.service.CourseService;
import com.ua.faculty.service.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class CoursesController {

    private final CourseService courseService;
    private final UserService userService;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String loadCourses(@RequestParam(value = "sort", required = false)
                            final String sortField,
                        @RequestParam(value = "search-by", required = false)
                        final String searchField,
                              @RequestParam(required = false) final String searchName, Model model) {
        Iterable<Course> coursesByParameters;
        if (searchField != null) {
            List<Course> courses = new ArrayList<>();
            courseService.getAllCourses().forEach(courses::add);
            coursesByParameters = courseService.searchBy(searchField, searchName);
        } else {
            coursesByParameters = courseService.getAllCourses();
        }
        if (sortField != null) {
            List<Course> courses = new ArrayList<>();
            coursesByParameters.forEach(courses::add);
            coursesByParameters = courseService.sort(
                    courses, sortField);
        }
        model.addAttribute("courses", coursesByParameters);
        model.addAttribute("localDate", LocalDate.now());
        return "courses";
    }

    @RequestMapping(value = "/create-course",
            method = RequestMethod.POST)
    public String createNewCourse(@ModelAttribute("courseDTO") CourseDTO course) {
        courseService.createCourse(course);
        return "redirect:/courses";
    }

    @RequestMapping(value = "/admin/courses/{id}/delete",
            method = RequestMethod.POST)
    public String deleteCourse(@PathVariable final String id) {
        courseService.deleteCourse(Long.parseLong(id));
        return "redirect:/courses";
    }


    @RequestMapping(value = "/admin/courses/{id}/edit",
            method = RequestMethod.POST)
    public String editCourse(@PathVariable final String id,
                             @ModelAttribute("courseDTO") CourseDTO course) {
        courseService.editCourse(course, Long.parseLong(id));
        return "redirect:/courses";
    }

    @SneakyThrows
    @RequestMapping(value = "/courses/{id}", method = RequestMethod.POST)
    public String enrollForCourse(HttpServletRequest request,
                                  @PathVariable final String id) {
        Principal principal = request.getUserPrincipal();
        userService.enrollForCourse(principal.getName(), Long.parseLong(id));
        return "redirect:/profile";
    }

    @ExceptionHandler({StudentAlreadyEnrolledException.class})
    public String handleException(RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("userAlreadyEnrolled", "");

        return "redirect:/courses";
    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.GET)
    public String showCourse(Model model,
                                  @PathVariable final String id) {
        model.addAttribute("course", courseService
                                            .getCourseById(Long.parseLong(id)));
        return "one_course";
    }

    @RequestMapping(value = "/journal/courses/{id}", method = RequestMethod.GET)
    public String showJournal(Model model,
                              @PathVariable final String id) {
        model.addAttribute("studentRates",
                                        courseService
                                                .getAllRatesByCourse(
                                                        Long.parseLong(id)));
        model.addAttribute("courseId", id);
        return "journal";
    }

    @RequestMapping(value = "/journal/courses/{courseId}", method = RequestMethod.POST)
    public String editJournal(@PathVariable final String courseId,
                              @RequestParam String userId,
                              @RequestParam(value = "rate") String rate) {
        courseService.setCourseRating(Long.parseLong(courseId),
                Long.parseLong(userId),
                Integer.parseInt(rate));
        return "redirect:/journal/courses/" + courseId;
    }


}
