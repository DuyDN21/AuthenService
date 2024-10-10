package com.authentication.AuthenService.Controllers;

import com.authentication.AuthenService.Models.RequestModels.LoginRequest;
import com.authentication.AuthenService.Models.DatabaseModels.User;
import com.authentication.AuthenService.Models.RequestModels.RegisterRequest;
import com.authentication.AuthenService.Models.ResponseModels.LoginResponse;
import com.authentication.AuthenService.Models.ResponseModels.RegisterResponse;
import com.authentication.AuthenService.Services.Interfaces.IAuthenticationService;
import com.authentication.Infrastructures.Enums.ResponseCodes.LoginResponseCodes;
import com.authentication.Infrastructures.Enums.ResponseCodes.RegisterResponseCodes;
import com.authentication.Utils.HashingUtil;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> DoLogin(@RequestBody LoginRequest req) {
        Either<LoginResponseCodes, Tuple2<User, LoginResponseCodes>> result = authenticationService.DoLogin(req.username(), req.password());

        //login fail
        if(result.isLeft())
            return new ResponseEntity<>(
                    new LoginResponse(result.getLeft().getCode(), result.getLeft().getDesc(), null),
                    HttpStatus.OK);

        //login success
        //create access token

        //create cookie for access token

        //create refresh token

        //save refresh token to DB

        //create cookie for refresh token

        return new ResponseEntity<>(
                new LoginResponse(result.get()._2.getCode(), result.get()._2.getDesc(), result.get()._1()),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<RegisterResponse> DoRegister(@RequestBody RegisterRequest req) {
        Either<RegisterResponseCodes, Tuple2<User, RegisterResponseCodes>> result =
                authenticationService.DoRegister(
                        new User(UUID.randomUUID().toString(), req.username(), req.password(), req.fullName(), req.dob().orElse(null), req.email(), true),
                        req.confirmPassword());

        if(result.isLeft())
            return new ResponseEntity<>(
                    new RegisterResponse(result.getLeft().getCode(), result.getLeft().getDesc(), null),
                    HttpStatus.OK
            );

        //register success
        return new ResponseEntity<>(
                new RegisterResponse(result.get()._2().getCode(), result.get()._2().getDesc(), result.get()._1()),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/getSalt", method = RequestMethod.GET)
    public String GenSalt(){
        return HashingUtil.GenerateSalt();
    }
}
