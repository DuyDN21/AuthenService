package com.authentication.AuthenService.Services.Implementations;

import com.authentication.AuthenService.DataAccess.Interfaces.IUserRepository;
import com.authentication.AuthenService.Models.DatabaseModels.User;
import com.authentication.AuthenService.Services.Interfaces.IAuthenticationService;
import com.authentication.Infrastructures.Enums.ResponseCodes.LoginResponseCodes;
import com.authentication.Utils.HashingUtil;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Either<String, Tuple2<User, String>> DoLogin(String username, String password) {
        User user = userRepository.GetByUsername(username);

        //check if user exists
        if(user == null)
            return Either.left(LoginResponseCodes.LOGIN_FAILED.getCode());
        //verify password
        if(!HashingUtil.CheckPassword(password, user.getPassword()))
            return Either.left(LoginResponseCodes.LOGIN_FAILED.getCode());
        //check user status
        if(!user.isActive())
            return Either.left(LoginResponseCodes.USER_DISABLED.getCode());

        user.setPassword("");
        return Either.right(new Tuple2<>(user, LoginResponseCodes.LOGIN_SUCCESS.getCode()));
    }

    @Override
    public Either<String, Tuple2<User, String>> DoRegister(User user) {
        return null;
    }

    @Override
    public Either<String, Tuple2<User, String>> ChangePassword(String username, String oldPassword, String newPassword) {
        return null;
    }
}
