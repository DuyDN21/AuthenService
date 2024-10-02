package com.authentication.AuthenService.Controllers;

import com.authentication.AuthenService.Models.User;
import com.authentication.AuthenService.Services.Interfaces.IAuthenticationService;
import com.authentication.Utils.HashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authen")
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> DoLogin(@RequestBody LoginRequest req) {
        String username = req.getUsername();
        String password = req.getPassword();

        int result = authenticationService.DoLogin(username, password);
        MultiValueMap<String, String> msg = new LinkedMultiValueMap<>();

        return switch (result) {
            case -1 -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            case 0 -> {
                msg.add("Message", "Wrong pw!");
                yield new ResponseEntity<>(null, msg, HttpStatus.OK);
            }
            case 1 -> {
                msg.add("Message", "User disabled!");
                yield new ResponseEntity<>(null, msg, HttpStatus.OK);
            }
            case 2 -> {
                msg.add("Message", "Login OK!");
                yield new ResponseEntity<>(null, msg, HttpStatus.OK);
            }
            default -> null;
        };
    }

}
