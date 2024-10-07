package com.authentication.AuthenService.DataAccess.Implementations;

import com.authentication.AuthenService.DataAccess.Interfaces.IUserRepository;
import com.authentication.AuthenService.Models.DatabaseModels.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    EntityManager entityManager;

    @Override
    public boolean IsUserExists(String username) {
        return !(GetByUsername(username) == null);
    }

    @Override
    public User GetByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User u WHERE Username = :username");
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public User Create(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(user);
        transaction.commit();
        currentSession.close();
        return user;
    }

    @Override
    public List<User> GetAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from user", User.class);
        return query.getResultList();
    }

    @Override
    public int Update(User user) {
        if(GetByUsername(user.getUsername()) == null){
           return 0;
        }else{
            Session currentSession = entityManager.unwrap(Session.class);
            String updateQuery = "UPDATE user SET " +
                    "full_name = :full_name," +
                    "dob = :dob," +
                    "email = :email," +
                    "WHERE username = :username;" +
                    "SELECT ROW_COUNT();";
            Query<Integer> query = currentSession.createQuery(updateQuery, Integer.class);
            query.setParameter("full_name", user.getFullName());
            query.setParameter("email", user.getEmail());
            query.setParameter("dob", user.getDob());
            query.setParameter("username", user.getUsername());

            return query.getResultList().stream().findFirst().orElse(0);
        }
    }

    @Override
    public int UpdateStatus(User user) {
        if(GetByUsername(user.getUsername()) == null){
            return 0;
        }else{
            Session currentSession = entityManager.unwrap(Session.class);
            String updateQuery = "UPDATE user SET " +
                    "is_active = :is_active," +
                    "WHERE username = :username;" +
                    "SELECT ROW_COUNT();";
            Query<Integer> query = currentSession.createQuery(updateQuery, Integer.class);
            query.setParameter("is_active", user.isActive());
            query.setParameter("username", user.getUsername());

            return query.getResultList().stream().findFirst().orElse(0);
        }
    }

    @Override
    public int UpdatePassword(User user) {
        if(GetByUsername(user.getUsername()) == null){
            return 0;
        }else{
            Session currentSession = entityManager.unwrap(Session.class);
            String updateQuery = "UPDATE user SET " +
                    "password = :password," +
                    "WHERE username = :username;" +
                    "SELECT ROW_COUNT();";
            Query<Integer> query = currentSession.createQuery(updateQuery, Integer.class);
            query.setParameter("password", user.getPassword());
            query.setParameter("username", user.getUsername());

            return query.getResultList().stream().findFirst().orElse(0);
        }
    }

    @Override
    public int Delete(User user) {
        if(GetByUsername(user.getUsername()) == null){
            return 0;
        }else{
            Session currentSession = entityManager.unwrap(Session.class);
            String updateQuery = "DELETE FROM user " +
                    "WHERE username = :username;" +
                    "SELECT ROW_COUNT();";
            Query<Integer> query = currentSession.createQuery(updateQuery, Integer.class);
            query.setParameter("username", user.getUsername());

            return query.getResultList().stream().findFirst().orElse(0);
        }
    }
}
