package com.authentication.AuthenService.Services.Implementations;

import com.authentication.AuthenService.DataAccess.Interfaces.IUserRepository;
import com.authentication.AuthenService.Models.DTOs.UserLoginDTO;
import com.authentication.AuthenService.Models.DatabaseModels.User;
import com.authentication.AuthenService.Services.Interfaces.IAuthenticationService;
import com.authentication.Infrastructures.Enums.ResponseCodes.LoginResponseCodes;
import com.authentication.Infrastructures.Enums.ResponseCodes.RegisterResponseCodes;
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
    public Either<LoginResponseCodes, Tuple2<UserLoginDTO, LoginResponseCodes>> DoLogin(String username, String password) {
        User user = userRepository.GetByUsername(username);

        //check if user exists
        if(user == null)
            return Either.left(LoginResponseCodes.LOGIN_FAILED);
        //verify password
        if(!HashingUtil.CheckPassword(password, user.getPassword()))
            return Either.left(LoginResponseCodes.LOGIN_FAILED);
        //check user status
        if(!user.isActive())
            return Either.left(LoginResponseCodes.USER_DISABLED);

        UserLoginDTO u = new UserLoginDTO(
                user.getUsername(),
                user.getFullName(),
                user.getDob(),
                user.getEmail(),
                user.isActive(),
                user.getRefreshToken(),
                user.getRefreshToken()
        );

        return Either.right(new Tuple2<>(u, LoginResponseCodes.LOGIN_SUCCESS));
    }

    @Override
    public Either<RegisterResponseCodes, Tuple2<User, RegisterResponseCodes>> DoRegister(User user, String confirmPassword) {
        User u = userRepository.GetByUsername(user.getUsername());

        //check username exist
        if(u != null)
            return Either.left(RegisterResponseCodes.USERNAME_EXISTED);
        //check password and confirm password
        if(!user.getPassword().equals(confirmPassword))
            return Either.left(RegisterResponseCodes.CONFIRM_PW_NOT_MATCH);

        String salt = HashingUtil.GenerateSalt();

        user.setPassword(HashingUtil.HashPassword(user.getPassword(), salt));
        user.setSalt(salt);

        userRepository.Create(user);

        user.setPassword("");
        user.setSalt("");

        return Either.right(new Tuple2<>(user, RegisterResponseCodes.REGISTER_SUCCESS));
    }

    @Override
    public Either<String, Tuple2<User, String>> ChangePassword(String username, String oldPassword, String newPassword) {
        return null;
    }
}
