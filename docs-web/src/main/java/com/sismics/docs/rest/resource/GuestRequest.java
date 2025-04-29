package com.sismics.docs.rest.resource;

import java.util.Date;
import java.util.UUID;

public class GuestRequest {
    private String id;
    private String username;
    private String email;
    private String password;
    private String status; // "PENDING", "APPROVED", "REJECTED"
    private Date applyDate;
    private Date reviewDate;

    public GuestRequest() {
        this.id = UUID.randomUUID().toString();
        this.applyDate = new Date();
        this.status = "PENDING";
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
