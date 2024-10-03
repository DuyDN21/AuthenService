package com.authentication.AuthenService.Controllers;

import com.authentication.AuthenService.Models.RequestModels.LoginRequest;
import com.authentication.AuthenService.Models.DatabaseModels.User;
import com.authentication.AuthenService.Models.ResponseModels.LoginResponse;
import com.authentication.AuthenService.Services.Interfaces.IAuthenticationService;
import com.authentication.Infrastructures.Enums.ResponseCodes.LoginResponseCodes;
import io.vavr.Tuple2;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> DoLogin(@RequestBody LoginRequest req) {
        Either<String, Tuple2<User, String>> result = authenticationService.DoLogin(req.username(), req.password());

        if(result.isLeft())
            return new ResponseEntity<>(
                    new LoginResponse(result.getLeft(), LoginResponseCodes.LOGIN_FAILED.getDesc(), null),
                    HttpStatus.OK);

        return new ResponseEntity<>(
                new LoginResponse(result.get()._2(), LoginResponseCodes.LOGIN_SUCCESS.getDesc(), result.get()._1()),
                HttpStatus.OK
        );
    }

}
