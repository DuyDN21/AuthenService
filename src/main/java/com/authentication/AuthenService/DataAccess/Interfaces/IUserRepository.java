package com.authentication.AuthenService.DataAccess.Interfaces;

import com.authentication.AuthenService.Models.DatabaseModels.User;

import java.util.List;

public interface IUserRepository {

    boolean IsUserExists(String username);
    User GetByUsername(String userId);
    User Create(User user);
    List<User> GetAll();
    int Update(User user);
    int UpdateStatus(User user);
    int UpdatePassword(User user);
    int Delete(User user);

}
