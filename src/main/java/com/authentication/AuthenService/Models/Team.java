package com.authentication.AuthenService.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "table")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private int TeamId;
    @Column private String TeamName;
    @Column private Date CreatedDate;
    @Column private boolean IsActive;

    public Team() {
    }

    public Team(String teamName, Date createdDate, boolean isActive) {
        TeamName = teamName;
        CreatedDate = createdDate;
        IsActive = isActive;
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }
}
