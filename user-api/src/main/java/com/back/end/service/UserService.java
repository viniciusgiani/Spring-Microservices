package com.back.end.service;

import com.back.end.converter.DTOConverter;
import com.back.end.dto.UserDTO;
import com.back.end.exception.UserNotFoundException;
import com.back.end.model.User;
import com.back.end.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public Page<UserDTO> getAllPage(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users
                .map(DTOConverter::convert);
    }

    public UserDTO findById(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        return DTOConverter.convert(user);
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID().toString());
        userDTO.setRegisterDate(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return DTOConverter.convert(user);
    }

    public void delete(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(user);
    }

    public UserDTO findBySsn(String ssn, String key) {
        User user = userRepository.findBySsnAndKey(ssn, key);
        if (user != null) {
            return DTOConverter.convert(user);
        }
        throw new UserNotFoundException();
    }

    public List<UserDTO> queryByName(String name) {
        List<User> users = userRepository.queryByNameLike(name);
        return users.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public UserDTO editUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException());
        if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getCellphone() != null && !user.getCellphone().equals(userDTO.getCellphone())) {
            user.setCellphone(userDTO.getCellphone());
        }
        if (userDTO.getAddress() != null && !user.getAddress().equals(userDTO.getAddress())) {
            user.setAddress(userDTO.getAddress());
        }

        user = userRepository.save(user);
        return DTOConverter.convert(user);
    }
}
