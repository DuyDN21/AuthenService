package com.authentication.AuthenService.Services.Interfaces;

import com.authentication.AuthenService.Models.User;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {

    int DoLogin(String username, String password);
    ResponseEntity<User> DoRegister(User user);
    ResponseEntity<User> ChangePassword(String username, String oldPassword, String newPassword);

}
