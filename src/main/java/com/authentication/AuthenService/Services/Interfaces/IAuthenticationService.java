package com.authentication.AuthenService.Services.Interfaces;

import com.authentication.AuthenService.Models.DTOs.UserLoginDTO;
import com.authentication.AuthenService.Models.DatabaseModels.User;
import com.authentication.Infrastructures.Enums.ResponseCodes.LoginResponseCodes;
import com.authentication.Infrastructures.Enums.ResponseCodes.RegisterResponseCodes;
import io.vavr.Tuple2;
import io.vavr.control.Either;

public interface IAuthenticationService {

    Either<LoginResponseCodes, Tuple2<UserLoginDTO, LoginResponseCodes>> DoLogin(String username, String password);
    Either<RegisterResponseCodes, Tuple2<User, RegisterResponseCodes>> DoRegister(User user, String confirmPassword);
    Either<String, Tuple2<User, String>> ChangePassword(String username, String oldPassword, String newPassword);

}
