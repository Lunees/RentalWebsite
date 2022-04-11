package com.edugrade.rentalwebsite.services;

import com.edugrade.rentalwebsite.entities.UserAccount;
import com.edugrade.rentalwebsite.entities.Role;

import java.util.List;

public interface UserAccountService {
    UserAccount saveUser(UserAccount user);
    Role saveRole(Role role);
    void addRoleToUserAccount(String username, String roleName);
    UserAccount getUser(String username);
    List<UserAccount>getUsers();
}
