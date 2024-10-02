package com.authentication.AuthenService.DataAccess.Interfaces;

import com.authentication.AuthenService.Models.User;

import java.util.List;

public interface IUserRepository {

    boolean IsUserExists(String username);
    User GetByUsername(String userId);
    List<User> GetAll();
    int Update(User user);
    int UpdateStatus(User user);
    int UpdatePassword(User user);
    int Delete(User user);

}
