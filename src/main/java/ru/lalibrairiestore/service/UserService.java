package ru.lalibrairiestore.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lalibrairiestore.dto.AuthenticationRequestDTO;
import ru.lalibrairiestore.dto.UserDTO;
import ru.lalibrairiestore.dto.UserRegistrationDTO;
import ru.lalibrairiestore.exceptions.AuthenticationServiceException;
import ru.lalibrairiestore.exceptions.BadRequestException;
import ru.lalibrairiestore.exceptions.EntityNotFoundException;
import ru.lalibrairiestore.mapper.UserMapper;
import ru.lalibrairiestore.model.Role;
import ru.lalibrairiestore.model.User;
import ru.lalibrairiestore.repository.UserRepository;
import ru.lalibrairiestore.security.JwtTokenProvider;
import ru.lalibrairiestore.security.JwtUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * User registration
     */
    public UserDTO registration(UserRegistrationDTO userRegistrationDTO) {

        User user = userMapper.userRegistrationDTOToUser(userRegistrationDTO);

        if (userRepository.findUserByLogin(userRegistrationDTO.getLogin()).isPresent()) {
            throw new BadRequestException("User with such login already exists!");
        }

        if (userRegistrationDTO.getPassword().equals(userRegistrationDTO.getCheckPassword())) {
            user.setPassword(userRegistrationDTO.getPassword());
        } else {
            throw new BadRequestException("Passwords are different");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setDeleted(false);
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);

        log.info("IN register - user: {} successfully registered", user);

        return userMapper.userToUserDto(user);
    }

    /**
     * User authorization
     */
    public ResponseEntity<Map<String, String>> authorization(
            @RequestBody AuthenticationRequestDTO authenticationRequestDTO) {

        try {
            String login = authenticationRequestDTO.getLogin();
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            login, authenticationRequestDTO.getPassword()));

            User user = findUserByLogin(login);

            if (user == null) {
                throw new AuthenticationServiceException("Login not found.");
            }

            String token = jwtTokenProvider.createToken(login, user.getRole());

            Map<String, String> response = new HashMap<>();
            response.put("login", login);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationServiceException error) {
            log.error("IN authorization : ", error);
            throw new AuthenticationServiceException("Invalid login");
        }
    }

    /**
     * Find all users
     */
    public List<UserDTO> findAll() {

        List<UserDTO> usersList = userMapper.userListToDTOUserList(userRepository.findAll());
        log.info("IN findAll {} users were found", usersList.size());

        return usersList;
    }

    /**
     * Find user by user id
     */
    public UserDTO findUserById(Long id) {

        return userMapper.userToUserDto(userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("IN findUserById - user wasn't found by id: {}", id);
                    return new EntityNotFoundException("User id " + id + " was not found.");
                }));
    }

    /**
     * Find user by login
     *
     * @return UserDTO
     */
    public UserDTO findByLogin(String login) {

        return userMapper.userToUserDto(userRepository.findUserByLogin(login)
                .orElseThrow(() -> {
                    log.error("IN findByLogin - user wasn't found by login: {}", login);
                    return new EntityNotFoundException("Login " + login + " was not found.");
                }));
    }

    /**
     * Find user by login
     *
     * @return User
     */
    public User findUserByLogin(String login) {

        return userRepository.findUserByLogin(login)
                .orElseThrow(() -> {
                    log.error("IN findUserByLogin - user wasn't found by login: {}", login);
                    return new EntityNotFoundException("Login " + login + " was not found.");
                });
    }

    /**
     * Find user by email
     */
    public UserDTO findUserByEMail(String email) {

        return userMapper.userToUserDto(userRepository.findUserByEmail(email)
                .orElseThrow(() -> {
                    log.error("IN findUserByEMail - user wasn't found by email: {}", email);
                    return new EntityNotFoundException("Email " + email + " was not found.");
                }));
    }

    /**
     * Find user by phone number
     */
    public UserDTO findUserByPhoneNumber(String phoneNumber) {

        return userMapper.userToUserDto(userRepository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> {
                    log.info("IN findUserByPhoneNumber - user wasn't found by phone number: {}", phoneNumber);
                    return new EntityNotFoundException("Email " + phoneNumber + " was not found.");
                }));
    }

    public Long changePassword(UserRegistrationDTO userRegistrationDTO) {

        Long userId = JwtUser.getCurrentUserID();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User id " + userId + " was not found"));


        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getCheckPassword())) {
            throw new BadRequestException("Passwords are different");
        }

        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        userRepository.save(user);

        log.info("IN changePassword - user: {} successfully changed password", user);
        return userId;
    }
}
