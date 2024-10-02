package com.authentication.AuthenService.Services.Implementations;

import com.authentication.AuthenService.DataAccess.Interfaces.IUserRepository;
import com.authentication.AuthenService.Models.User;
import com.authentication.AuthenService.Services.Interfaces.IAuthenticationService;
import com.authentication.Utils.HashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public int DoLogin(String username, String password) {
        User user = userRepository.GetByUsername(username);
        MultiValueMap<String, String> msg = new LinkedMultiValueMap<>();

        //check if user exists
        if(user == null)
            return -1;
        //verify password
        if(!HashingUtil.CheckPassword(password, user.getPassword()))
            return 0;
        //check user status
        if(!user.isActive())
            return 1;

        return 2;
    }

    @Override
    public ResponseEntity<User> DoRegister(User user) {
        return null;
    }

    @Override
    public ResponseEntity<User> ChangePassword(String username, String oldPassword, String newPassword) {
        return null;
    }
}
