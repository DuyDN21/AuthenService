package com.authentication.AuthenService.Models.RequestModels;

import java.util.Date;
import java.util.Optional;

public record RegisterRequest(
        String username,
        String password,
        String confirmPassword,
        String fullName,
        String email,
        Optional<Date> dob) { }
