package com.yourhome.service;

import com.yourhome.dto.BookingUserDto;
import com.yourhome.dto.LoginDto;
import com.yourhome.entity.BookingUser;
import com.yourhome.repository.BookingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BookingUserRepository bookingUserRepository;

    private JWTService jwtService;

    public UserService(BookingUserRepository bookingUserRepository, JWTService jwtService) {
        this.bookingUserRepository = bookingUserRepository;
        this.jwtService = jwtService;
    }

    public BookingUser userRegistration(BookingUserDto bookingUserDto){

        BookingUser bookingUser=new BookingUser();
        bookingUser.setFirstName(bookingUserDto.getFirstName());
        bookingUser.setLastName(bookingUserDto.getLastName());
        bookingUser.setEmail(bookingUserDto.getEmail());
        bookingUser.setUsername(bookingUserDto.getUsername());
        bookingUser.setPassword(BCrypt.hashpw(bookingUserDto.getPassword(),BCrypt.gensalt(10)));
         bookingUser.setUserRole(bookingUserDto.getUserRole());

        BookingUser registeredUser = bookingUserRepository.save(bookingUser);

        return  registeredUser;

    }


    public String userLogin(LoginDto loginDto) {
        Optional<BookingUser> byUsername = bookingUserRepository.findByUsername(loginDto.getUsername());

        if (byUsername.isPresent()){
            BookingUser bookingUser = byUsername.get();
            if( BCrypt.checkpw(loginDto.getPassword(),bookingUser.getPassword())){
               return jwtService.tokenGenerator(bookingUser);
            }

        }
        return null;


    }
}
