package ru.lalibrairiestore.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.exceptions.AuthenticationServiceException;
import ru.lalibrairiestore.model.User;
import ru.lalibrairiestore.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) {

        User user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new AuthenticationServiceException(
                        "User doesn't exist."));

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with login: {} successfully loaded", login);
        return jwtUser;
    }
}
