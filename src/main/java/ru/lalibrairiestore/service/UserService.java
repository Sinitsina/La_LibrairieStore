package ru.lalibrairiestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.dto.UserDTO;
import ru.lalibrairiestore.dto.UserRegistrationDTO;
import ru.lalibrairiestore.exceptions.BadRequestException;
import ru.lalibrairiestore.exceptions.EntityNotFoundException;
import ru.lalibrairiestore.mapper.UserMapper;
import ru.lalibrairiestore.model.Role;
import ru.lalibrairiestore.model.User;
import ru.lalibrairiestore.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
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

        user.setDeleted(false);
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

    /**
     * Find all users
     */
    public List<UserDTO> findAll() {

        return userMapper.userListToDTOUserList(userRepository.findAll());
    }

    /**
     * Find user by user id
     */
    public UserDTO findUserById(Long id) {

        return userMapper.userToUserDto(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User id " + id + " was not found.")));
    }

    /**
     * Find user by login
     */
    public UserDTO findUserByLogin(String login) {

        return userMapper.userToUserDto(userRepository.findUserByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("Login " + login + " was not found.")));
    }

    /**
     * Find user by email
     */
    public UserDTO findUserByEMail(String EMail) {

        return userMapper.userToUserDto(userRepository.findUserByEMail(EMail)
                .orElseThrow(() -> new EntityNotFoundException("Email " + EMail + " was not found.")));
    }

    /**
     * Find user by phone number
     */
    public UserDTO findUserByPhoneNumber(String phoneNumber) {

        return userMapper.userToUserDto(userRepository.findUserByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new EntityNotFoundException("Email " + phoneNumber + " was not found.")));
    }
}
