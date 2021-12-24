package com.example.lab5.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws
            UsernameNotFoundException {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public User signUpUser(User user, UserInfo userInfo,
                           String roleName) throws UserAlreadyExistsException {
        if (emailOrLoginExists(userInfo.getEmail(), user.getLogin())) {
            throw new UserAlreadyExistsException();
        }
        user.setRoles(Collections.singleton(roleRepository.findByName(roleName)
                .orElseThrow(() -> new UsernameNotFoundException(""))));
        user.setUserInfo(userInfo);
        userInfo.setUser(user);
        userRepository.save(user);
        userInfoRepository.save(userInfo);
        return user;
    }

    private boolean emailOrLoginExists(String email, String login) {
        return userInfoRepository.findByEmail(email).isPresent()
                || userRepository.findByLogin(login).isPresent();
    }

    public User changeBlockStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        user.setBlocked(!user.getBlocked());
        userRepository.save(user);
        return user;
    }

    public User getTeacherByFullName(String fullName) {
        Set<User> teachers = getAllTeachers();
        return teachers.stream()
                .filter(teacher -> teacher.getUserInfo()
                        .toString().equals(fullName))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public Set<User> getAllTeachers() {
        return userRepository.findAllByRoles(roleRepository
                .findByName("teacher")
                .orElseThrow(() -> new UsernameNotFoundException("")))
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    public User getUserByLogin(String login) {
        return userRepository
                .findByLogin(login).get();
    }

    public Set<User> getUsersByRoleName(String roleName) {
        return userRepository.findAllByRoles(
                roleRepository.findByName(roleName)
                        .orElseThrow(() -> new UsernameNotFoundException(""))
        ).get();
    }

    public void enrollForCourse(String studentLogin, Long courseId)
            throws StudentAlreadyEnrolledException {
        CourseRating rating = new CourseRating();
        User student = userRepository.findByLogin(studentLogin)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        if (courseRatingService
                    .checkIfUserIsEnrolled(student.getId(), courseId)) {
            throw new StudentAlreadyEnrolledException("");
        }
        rating.setUser(student);
        rating.getId().setStudentId(student.getId());
        courseRatingService.enrollForCourse(rating, courseId);
    }


}
