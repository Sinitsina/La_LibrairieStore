package ru.lalibrairiestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lalibrairiestore.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByEMail(String EMail);

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    User findUserById(Long id);

}