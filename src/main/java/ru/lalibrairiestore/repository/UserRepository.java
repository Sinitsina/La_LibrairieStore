package ru.lalibrairiestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.lalibrairiestore.model.User;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLogin(String login);

//    @Query('select e from db_users')
//    Set<User> findAllByName(String name);
}