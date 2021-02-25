package ru.lalibrairiestore.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lalibrairiestore.dto.UserDTO;
import ru.lalibrairiestore.mapper.UserMapper;
import ru.lalibrairiestore.model.User;
import ru.lalibrairiestore.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Autowired
//    public void setUserMapper(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserDTO findUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("User was not found")));
    }

//    public registration(User user) {
//        userRepository.save();
//    }
}
