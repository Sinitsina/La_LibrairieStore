package ru.lalibrairiestore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.dto.UserDTO;
import ru.lalibrairiestore.dto.UserRegistrationDTO;
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



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDTO findUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("User was not found")));
    }

    public UserDTO registration(UserRegistrationDTO userRegistrationDTO) {

        User user = userMapper.userRegistrationDTOToUser(userRegistrationDTO);

        if (userRepository.findUserByLogin(userRegistrationDTO.getLogin()) != null) {
            throw new IllegalArgumentException("User with such login already exists");
        }

        if (userRegistrationDTO.getPassword().equals(userRegistrationDTO.getCheckPassword())) {
            user.setPassword(userRegistrationDTO.getPassword());
        } else {
            throw new IllegalArgumentException("Passwords are different");
        }

        user.setDeleted(false);
        user.setRole(Role.USER);

        userRepository.save(user);

        return userMapper.userToUserDto(user);
    }

}
