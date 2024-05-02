package com.yourhome.repository;

import com.yourhome.entity.BookingUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingUserRepository extends JpaRepository<BookingUser, Long> {

    Optional<BookingUser> findByUsername(String username);
}