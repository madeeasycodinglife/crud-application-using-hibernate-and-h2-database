package com.madeeasy.dao;

import com.madeeasy.entity.Users;

import java.util.List;

public interface UserDAO {
    void save(Users user);

    Users findByEmail(String email);

    void deleteByEmail(String email);

    List<Users> getAllUsers();

    void update(Users user);
}
