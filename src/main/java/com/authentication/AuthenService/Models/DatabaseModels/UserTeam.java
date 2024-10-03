package com.authentication.AuthenService.Models.DatabaseModels;

import jakarta.persistence.*;

@Entity
@Table(name = "user_team")
public class UserTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private int Id;
    @Column private String UserId;
    @Column private int TeamId;
    @Column private int RoleId;

    public UserTeam() {
    }

    public UserTeam(String userId, int teamId, int roleId) {
        UserId = userId;
        TeamId = teamId;
        RoleId = roleId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }
}
