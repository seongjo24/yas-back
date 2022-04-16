package com.yas.backend.domain.user.controller;

import com.yas.backend.domain.user.data.dto.UserDto;
import com.yas.backend.domain.user.data.mapper.UserMapper;
import com.yas.backend.domain.user.data.response.LoginResponse;
import com.yas.backend.domain.user.data.response.UserResponse;
import com.yas.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("s")
    public List<UserResponse> findAllActiveUser() {
        return userService.findAllActiveUser().stream().map(userMapper::dtoToResponse).toList();
    }

    @GetMapping()
    public UserResponse findActiveUserByEmail(@Param("email") String email) {
        return userMapper.dtoToResponse(userService.findUserByEmail(email));
    }

    @PostMapping("/join")
    public UserResponse joinUser(UserDto userDto) {
        return userMapper.dtoToResponse(userService.joinUser(userDto));
    }

    @PostMapping("/login")
    public LoginResponse login(UserDto userDto) {
        return userService.login(userDto);
    }

}