package com.madeeasy.service;

import com.madeeasy.entity.Users;

import java.util.List;

public interface UserService {

    Users findByEmail(String email);
    void save();
    void update();
    void getAllUsers();
    void deleteByEmail();
}
