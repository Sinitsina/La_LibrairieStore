package ru.lalibrairiestore.security;

import ru.lalibrairiestore.model.User;


public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.isDeleted(),
                user.getRole()
        );
    }
}