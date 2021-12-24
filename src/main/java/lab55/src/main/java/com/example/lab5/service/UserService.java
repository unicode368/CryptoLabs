package com.example.lab5.service;

import com.example.lab5.entity.User;
import com.example.lab5.entity.UserInfo;
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


    public User getUserByLogin(String login) {
        return userRepository
                .findByLogin(login).get();
    }



}
