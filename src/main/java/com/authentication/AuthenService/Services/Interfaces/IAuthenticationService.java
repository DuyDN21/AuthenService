package com.authentication.AuthenService.Services.Interfaces;

import com.authentication.AuthenService.Models.DatabaseModels.User;
import io.vavr.Tuple2;
import io.vavr.control.Either;

public interface IAuthenticationService {

    Either<String, Tuple2<User, String>> DoLogin(String username, String password);
    Either<String, Tuple2<User, String>> DoRegister(User user);
    Either<String, Tuple2<User, String>> ChangePassword(String username, String oldPassword, String newPassword);

}
