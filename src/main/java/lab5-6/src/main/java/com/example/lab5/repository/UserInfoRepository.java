package com.example.lab5.repository;

import com.example.lab5.entity.User;
import com.example.lab5.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmail(String email);
    Optional<Set<UserInfo>> findAllByUser(User user);
}
