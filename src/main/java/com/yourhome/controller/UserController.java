package com.yourhome.controller;

import com.yourhome.dto.BookingUserDto;
import com.yourhome.dto.LoginDto;
import com.yourhome.entity.BookingUser;
import com.yourhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/bookingUser")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/userRegistration")
    public ResponseEntity<String> userRegistration(@RequestBody BookingUserDto bookingUserDto){
        BookingUser bookingUser = userService.userRegistration(bookingUserDto);

        return new ResponseEntity<>("User Registered Successful", HttpStatus.CREATED);
        }

        @PostMapping("/userLogin")
        public ResponseEntity<String> userLogin(@RequestBody LoginDto loginDto){
            String status = userService.userLogin(loginDto);
            if (status!=null){
                return new ResponseEntity<>(status,HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);

        }


    }

