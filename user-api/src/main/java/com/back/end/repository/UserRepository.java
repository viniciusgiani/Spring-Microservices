package com.back.end.repository;

import com.back.end.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findBySsnAndKey(String ssn, String key);

    List<User> queryByNameLike(String name);

}
